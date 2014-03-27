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
	//MENU_ITEM_NUMBER used to ref 
    public static final String MENU_ITEM_NUMBER = "menu_number";
    //Options for the dialog box when a contact is selected
    private final CharSequence contactDialogMenu[] = new CharSequence[] {"Open Chat", "Send File", "View Transfers", "Edit Contact"};
    //ArrayList of displayed contacts
    ArrayList<ContactObject> contacts = new ArrayList<ContactObject>();
    //Init the ContactAdapter used to display contacts from the ArrayList contacts
    ContactAdapter contactAdapter = null;
    //Init the DatabaseHandler used to fetch contacts
    private DatabaseHandler db;
    
    //Empty constructor
    public ContactsFragment() {   	
    	
    }
    //Override onCreateView and inflate with contact list
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	//rootView holds the layout associated with the contacts menu
        View rootView = inflater.inflate(R.layout.contacts_fragment, container, false);
        //get the position of this menu object - Contacts is the first option
        int i = getArguments().getInt(MENU_ITEM_NUMBER);
        //get the menu options string at position i
        String menuItem = getResources().getStringArray(R.array.main_navigation_menu)[i];
        //set the activity title to Contacts 
        getActivity().setTitle(menuItem);
        //Return the view
        return rootView;
    }

    //fill the contact listview
    public void onActivityCreated (Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	try{
    		//init the database handler
			db = new DatabaseHandler(getActivity()); 
			//init the contactAdapter (used to fill the ListView with the fields we're interested in)
			contactAdapter = new ContactAdapter(getActivity(), R.layout.contacts_list_item, db.getAllContacts());
			
			db.close();
			//fetch the listview to be filled with contacts
	        final ListView listview = (ListView) getActivity().findViewById(R.id.list_container);
	        //associate the adapter
	        listview.setAdapter(contactAdapter); 
	        //there isn't actually any filtering logic yet
	        listview.setTextFilterEnabled(true);
	        //add onclick listeners to the contact list
	        listview.setOnItemClickListener(new OnItemClickListener()
	        {
		        public void onItemClick(AdapterView<?> arg0, View v, int position, long id)
		        {
		                
			        //get the ContactObject of the clicked contact
			        ContactObject co = (ContactObject)listview.getItemAtPosition(position);
			        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			        builder.setTitle(co.getFullName());
			        //add click listeners to the dialog options
			        builder.setItems(contactDialogMenu, new DialogInterface.OnClickListener() {
			        	//TODO: add logic, pass co along to the selected option
			            @Override
			            public void onClick(DialogInterface dialog, int dialogPosition) {
			                switch(dialogPosition){
			                //Open chat window with selected contact
			                case 0:
			                	break;
		                	//Open file browser to send file to selected contact 
			                case 1:
			                	break;
		                	//See if there are any currently transferring files to selected contact
			                case 2:
			                	break;
		                	//Edit the contact
			                case 3:
			                	break;
		                	default:
		                		break;
			                }
			            }
			        });
		        //display the dialog options
		        builder.show();
	            }
	        });
	        
    	}catch(Exception e){
    		e.printStackTrace();
    		Log.d("Error:",e.toString());
    	}
    }
}

