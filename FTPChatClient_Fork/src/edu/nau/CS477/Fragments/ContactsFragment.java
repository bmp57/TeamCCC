package edu.nau.CS477.Fragments;



import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.android.navigationdrawerexample.R;

import edu.nau.CS477.Classes.DatabaseHandler;
import edu.nau.CS477.Contacts.ContactAdapter;
import edu.nau.CS477.Contacts.ContactObject;

/**
 * Fragment that appears in the "content_frame"
 */
public class ContactsFragment extends Fragment {
    public static final String MENU_ITEM_NUMBER = "menu_number";
    private final CharSequence contactDialogMenu[] = new CharSequence[] {"Open Chat", "Send File", "View Transfers", "Edit Contact"};
    ArrayList<ContactObject> contacts = new ArrayList<ContactObject>();
    ContactAdapter contactAdapter = null;
    private DatabaseHandler db;
    public ContactsFragment() {
    	
    	
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contacts_fragment, container, false);
        
        int i = getArguments().getInt(MENU_ITEM_NUMBER);
        String menuItem = getResources().getStringArray(R.array.main_navigation_menu)[i];
        getActivity().setTitle(menuItem);
        return rootView;
    }

    public void onActivityCreated (Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	try{
			db = new DatabaseHandler(getActivity()); 
			contactAdapter = new ContactAdapter(getActivity(), R.layout.contacts_list_item, db.getAllContacts());
			db.close();
	        final ListView listview = (ListView) getActivity().findViewById(R.id.list_container);
	        listview.setAdapter(contactAdapter); 
	        listview.setTextFilterEnabled(true);
	        listview.setOnItemClickListener(new OnItemClickListener()
	        {
		        public void onItemClick(AdapterView<?> arg0, View v, int position, long id)
		        {
		                
			        
			        ContactObject co = (ContactObject)listview.getItemAtPosition(position);
			        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			        builder.setTitle(co.getFullName());
			        builder.setItems(contactDialogMenu, new DialogInterface.OnClickListener() {
			            @Override
			            public void onClick(DialogInterface dialog, int dialogPosition) {
			                switch(dialogPosition){
			                case 1:
			                	break;
			                case 2:
			                	break;
			                case 3:
			                	break;
			                case 4:
			                	break;
		                	default:
		                		break;
			                }
			            }
			        });
		        builder.show();
	            }
	        });
	        
    	}catch(Exception e){
    		e.printStackTrace();
    		Log.d("Error:",e.toString());
    	}
    }
}

