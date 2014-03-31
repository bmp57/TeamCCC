package edu.nau.CS477.Fragments;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import edu.nau.CS477.Classes.FileDialog;
import edu.nau.CS477.Classes.SelectionMode;
import com.example.android.navigationdrawerexample.R;

import edu.nau.CS477.Chat.MessageAdapter;
import edu.nau.CS477.Chat.MessageObject;
import edu.nau.CS477.Classes.ChatsDatabaseHandler;
import edu.nau.CS477.Contacts.ContactAdapter;
import edu.nau.CS477.Contacts.ContactObject;

/**
 * Fragment that appears in the "content_frame"
 */
public class ChatFragment extends Fragment {
    public static final String MENU_ITEM_NUMBER = "menu_number";
    private ContactObject co = null;
    private ChatsDatabaseHandler db;
    private ArrayList<MessageObject> messages = new ArrayList<MessageObject>();
    private MessageAdapter messageAdapter = null;
    
    private ListView listview;
    private View rootView;

    
    public ChatFragment() {
        
    }
    
        
        
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.chat_fragment, container, false);
        int i = getArguments().getInt(MENU_ITEM_NUMBER);
        String menuItem = getResources().getStringArray(R.array.main_navigation_menu)[i];
        co = getArguments().getParcelable("contactObject");
        if(co != null){
        	getActivity().setTitle(menuItem + " with " + co.getFullName());
        	
        }
        else 
        	getActivity().setTitle(menuItem);
       
        return rootView;
    }
    
    public void onActivityCreated (Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	final Button mSendMessage = (Button) getActivity().findViewById(R.id.sendBtn);
        mSendMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Date timeNow = new Date();
                SimpleDateFormat dateFmt = new SimpleDateFormat("MMMM d ");
                SimpleDateFormat timeFmt = new SimpleDateFormat("hh:mm a");
                EditText chatET = (EditText) getActivity().findViewById(R.id.chatET);
                //Create message
            	MessageObject msg = new MessageObject(co,timeFmt.format(timeNow), dateFmt.format(timeNow), chatET.getText().toString());
            	//Upload to database
            	db = new ChatsDatabaseHandler(getActivity());
            	db.addMessage(msg);
            	//Display - refresh display
            	messages = db.getMessage(co.getId());
    			db.close();
				
				messageAdapter = new MessageAdapter(getActivity(), R.layout.message_list_item, db.getMessage(co.getId()));
    			//fetch the listview to be filled with contacts    				
    	        listview = (ListView) getActivity().findViewById(R.id.list_messages);
    	        //associate the adapter
    	        listview.setAdapter(messageAdapter); 
    	        //there isn't actually any filtering logic yet
    	        listview.setTextFilterEnabled(true);
    			
            	db.close();
            	chatET.setText("");
            	
            }
            
        });
        
        final ImageButton mSendFile = (ImageButton) getActivity().findViewById(R.id.fileSendButton);
        mSendFile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Bundle args = new Bundle();
            	Fragment fragment = null;
            	fragment = new FileBrowserFragment();
                //args.putInt(FileBrowserFragment.MENU_ITEM_NUMBER, position); //pass contact arg
                fragment.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment).commit();
            }
            
        });
        
        try{
    		if(co != null){
    			db = new ChatsDatabaseHandler(getActivity()); 
    			messages = db.getMessage(co.getId());
    			db.close();
    			if(messages.size() != 0){
    				messageAdapter = new MessageAdapter(getActivity(), R.layout.message_list_item, db.getMessage(co.getId()));
	    			//fetch the listview to be filled with contacts    				
	    	        listview = (ListView) getActivity().findViewById(R.id.list_messages);
	    	        //associate the adapter
	    	        listview.setAdapter(messageAdapter); 
	    	        //there isn't actually any filtering logic yet
	    	        listview.setTextFilterEnabled(true);
    			}
    			else {
    				listview = (ListView) getActivity().findViewById(R.id.list_messages);
    				if (listview != null){
    					
    				}
    					
    			}
    		}
    		
    	}catch(Exception e){
    	}
    	
    }
    

}

