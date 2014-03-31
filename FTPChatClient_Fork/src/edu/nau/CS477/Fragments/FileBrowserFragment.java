package edu.nau.CS477.Fragments;



import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.navigationdrawerexample.R;

import edu.nau.CS477.Chat.MessageAdapter;
import edu.nau.CS477.Chat.MessageObject;
import edu.nau.CS477.Classes.ChatsDatabaseHandler;
import edu.nau.CS477.Classes.FileDialog;
import edu.nau.CS477.Classes.SelectionMode;
import edu.nau.CS477.Contacts.ContactObject;

/**
 * Fragment that appears in the "content_frame"
 */
public class FileBrowserFragment extends Fragment {
    public static final String MENU_ITEM_NUMBER = "menu_number";
    public View rootView;
    private ContactObject co = null;

    public FileBrowserFragment() {
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.file_browser_fragment, container, false);
        
        int i = getArguments().getInt(MENU_ITEM_NUMBER);
        String menuItem = getResources().getStringArray(R.array.main_navigation_menu)[i];
        getActivity().setTitle(menuItem);
        co = getArguments().getParcelable("contactObject");
        if(co != null){
        	((TextView) rootView.findViewById(R.id.textViewNameSend)).setText(co.getFullName());
        }
        return rootView;
    }
    
    public void onActivityCreated (Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
        
        final TextView mSendFile = (TextView) getActivity().findViewById(R.id.textViewChooseFile);
        mSendFile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(getActivity(), FileDialog.class);
                //intent.putExtra(FileDialog.START_PATH, "/sdcard");
                
                //can user select directories or not
                //intent.putExtra(FileDialog.CAN_SELECT_DIR, true);
                
                //alternatively you can set file filter
                //intent.putExtra(FileDialog.FORMAT_FILTER, new String[] { "png" });
    			
    			//rootView = inflater.inflate(R.layout.file_send_fragment_screen_detail,
        		//		container, false);
    			
    			intent.putExtra(FileDialog.SELECTION_MODE, SelectionMode.MODE_OPEN);
                
                startActivityForResult(intent, 1);
            }
            
        });
        
    	
    }
    
    public void onActivityResult(int requestCode, int resultCode,
            Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
            	String Path = data.getStringExtra(FileDialog.RESULT_PATH);
            	((TextView) rootView.findViewById(R.id.textViewFileSend))
    			.setText(Path);
            }
        }
    }
}

