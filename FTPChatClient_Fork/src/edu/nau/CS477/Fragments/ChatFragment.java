package edu.nau.CS477.Fragments;



import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.navigationdrawerexample.R;

import edu.nau.CS477.Classes.DatabaseHandler;
import edu.nau.CS477.Classes.MainActivity;

/**
 * Fragment that appears in the "content_frame"
 */
public class ChatFragment extends Fragment {
    public static final String MENU_ITEM_NUMBER = "menu_number";

    public ChatFragment() {
        
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
}

