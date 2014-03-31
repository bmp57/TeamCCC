package edu.nau.CS477.Fragments;



import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.nau.CS477.Classes.FileDialog;
import edu.nau.CS477.Classes.SelectionMode;
import com.example.android.navigationdrawerexample.R;

import edu.nau.CS477.Contacts.ContactObject;

/**
 * Fragment that appears in the "content_frame"
 */
public class ChatFragment extends Fragment {
    public static final String MENU_ITEM_NUMBER = "menu_number";
    ContactObject co;
    public View rootView;
    
    public ChatFragment() {
        
    }
    public ChatFragment(ContactObject co){
    	this.co = co;
    }
        
        
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chat_fragment, container, false);
        
        int i = getArguments().getInt(MENU_ITEM_NUMBER);
        String menuItem = getResources().getStringArray(R.array.main_navigation_menu)[i];
        getActivity().setTitle(menuItem);
       
        return rootView;
    }
    
    public void sendFile(View view){ //called when the sendFileButton is pressed
    	
    	Intent intent = new Intent(getActivity(), FileDialog.class); //create a File Browser intent
        //intent.putExtra(FileDialog.START_PATH, "/sdcard");
        
        //can user select directories or not
        //intent.putExtra(FileDialog.CAN_SELECT_DIR, true);
        
        //alternatively you can set file filter
        //intent.putExtra(FileDialog.FORMAT_FILTER, new String[] { "png" });
		
		//View rootView = inflater.inflate(R.layout.file_send_fragment_screen_detail,
		//		container, false);
		
		intent.putExtra(FileDialog.SELECTION_MODE, SelectionMode.MODE_OPEN);
        
        startActivityForResult(intent, 1);
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

