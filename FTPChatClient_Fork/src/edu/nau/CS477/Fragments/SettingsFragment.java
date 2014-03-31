package edu.nau.CS477.Fragments;



import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.navigationdrawerexample.R;

import edu.nau.CS477.Classes.ContactsDatabaseHandler;
import edu.nau.CS477.Contacts.ContactObject;

/**
 * Fragment that appears in the "content_frame"
 */
public class SettingsFragment extends Fragment {
    public static final String MENU_ITEM_NUMBER = "menu_number";
    private View mDeleteContactsButton;
    private ContactsDatabaseHandler db;
    public SettingsFragment() {
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.settings_fragment, container, false);
        
        int i = getArguments().getInt(MENU_ITEM_NUMBER);
        String menuItem = getResources().getStringArray(R.array.main_navigation_menu)[i];
        getActivity().setTitle(menuItem);
        return rootView;
    }
    
    public void onActivityCreated(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	mDeleteContactsButton = getActivity().findViewById(R.id.delete_contacts);
    	mDeleteContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
			public void onClick(View v) {
            	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            	    @Override
            	    public void onClick(DialogInterface dialog, int which) {
            	        switch (which){
            	        case DialogInterface.BUTTON_POSITIVE:
            	        	db = new ContactsDatabaseHandler(getActivity());
            				for(ContactObject co : db.getAllContacts()){
            					db.deleteContact(co);
            				}
            				db.close();
            	            break;

            	        case DialogInterface.BUTTON_NEGATIVE:
            	            //No button clicked
            	            break;
            	        }
            	    }
            	};
            	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            	builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
            	    .setNegativeButton("No", dialogClickListener).show();
		        
            }
        });
					
				
            
        }
    
   
}

