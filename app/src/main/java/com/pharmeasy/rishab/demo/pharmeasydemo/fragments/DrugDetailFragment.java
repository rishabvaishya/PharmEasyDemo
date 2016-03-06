package com.pharmeasy.rishab.demo.pharmeasydemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pharmeasy.rishab.demo.pharmeasydemo.R;
import com.pharmeasy.rishab.demo.pharmeasydemo.dataObjects.Drug;
import com.pharmeasy.rishab.demo.pharmeasydemo.db.DatabaseHelper;
import com.pharmeasy.rishab.demo.pharmeasydemo.db.MainApplication;

/**
 * Created by _MINDFREAK_ on 3/5/16.
 */

/**
 * A placeholder fragment containing a simple view.
 */
public class DrugDetailFragment extends Fragment
{
      /**
       * The fragment argument representing the section number for this
       * fragment.
       */
      private static final String ARG_SECTION_NUMBER = "section_number";

      /**
       * Returns a new instance of this fragment for the given section
       * number.
       */
      public static DrugDetailFragment newInstance( int sectionNumber )
      {
            DrugDetailFragment fragment = new DrugDetailFragment();
            Bundle             args     = new Bundle();
            args.putInt( ARG_SECTION_NUMBER, sectionNumber );
            fragment.setArguments( args );
            return fragment;
      }

      @Override
      public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
      {
            View rootView = inflater.inflate( R.layout.fragment_main, container, false );

            // get instance of databaseHandler
            DatabaseHelper db = MainApplication.getDatabaseHelper();

            Drug drug = db.getDrug( getArguments().getInt( ARG_SECTION_NUMBER ) );

            TextView textView = ( TextView ) rootView.findViewById( R.id.drug_name );
            textView.setText( drug.getName() );
            textView = ( TextView ) rootView.findViewById( R.id.manufacturer );
            textView.setText( drug.getManufacturer() );
            textView = ( TextView ) rootView.findViewById( R.id.price );
            textView.setText( "Rs " + drug.getPrice() );
            textView = ( TextView ) rootView.findViewById( R.id.form );
            textView.setText( drug.getForm() );
            RelativeLayout actionContainer;
            if ( drug.isAvalable() )
            {
                  actionContainer = ( RelativeLayout ) rootView.findViewById( R.id.action_container );
                  actionContainer.setBackgroundResource( R.drawable.oval_accent_yellow_circle );
                  ImageView buyImageView = ( ImageView ) rootView.findViewById( R.id.buy_image );
                  buyImageView.setVisibility( View.VISIBLE );
                  TextView outOfStock = ( TextView ) rootView.findViewById( R.id.sold_out );
                  outOfStock.setVisibility( View.INVISIBLE );
            }
            else
            {
                  actionContainer = ( RelativeLayout ) rootView.findViewById( R.id.action_container );
                  actionContainer.setBackgroundResource( R.drawable.oval_accent_red_circle );
                  ImageView buyImageView = ( ImageView ) rootView.findViewById( R.id.buy_image );
                  buyImageView.setVisibility( View.INVISIBLE );
                  TextView outOfStock = ( TextView ) rootView.findViewById( R.id.sold_out );
                  outOfStock.setVisibility( View.VISIBLE );
            }
            actionContainer.setOnClickListener( new View.OnClickListener()
            {
                  @Override
                  public void onClick( View v )
                  {
                        Toast.makeText( getActivity(), "Still Working on this feature", Toast.LENGTH_LONG ).show();
                  }
            } );
            ImageView image = ( ImageView ) rootView.findViewById( R.id.image );
            image.setImageResource( getDummyImage( getArguments().getInt( ARG_SECTION_NUMBER ) ) );
            return rootView;
      }

      private int getDummyImage( int position )
      {
            // the units place of the position number
            switch ( position % 10 )
            {
                  case 1:
                  case 6:
                        return R.drawable.image_1;
                  case 2:
                  case 7:
                        return R.drawable.image_2;
                  case 3:
                  case 8:
                        return R.drawable.image_3;
                  case 4:
                  case 9:
                        return R.drawable.image_4;
                  case 5:
                  case 0:
                        return R.drawable.image_5;
            }
            return R.drawable.image_1;
      }
}