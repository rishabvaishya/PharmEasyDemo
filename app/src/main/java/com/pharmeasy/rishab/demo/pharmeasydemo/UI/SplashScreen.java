package com.pharmeasy.rishab.demo.pharmeasydemo.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.pharmeasy.rishab.demo.pharmeasydemo.R;
import com.pharmeasy.rishab.demo.pharmeasydemo.common.AppVariables;
import com.pharmeasy.rishab.demo.pharmeasydemo.common.CommonMethods;
import com.pharmeasy.rishab.demo.pharmeasydemo.dataObjects.Drug;
import com.pharmeasy.rishab.demo.pharmeasydemo.db.DatabaseHelper;
import com.pharmeasy.rishab.demo.pharmeasydemo.db.MainApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SplashScreen extends AppCompatActivity
{

      @Override
      protected void onCreate( Bundle savedInstanceState )
      {
            // make activity full screen
            setActivityParams();

            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_splash );

            if ( isConnectedToInternet() )
            {
                  fetchDrugData();
            }
            else
            {
                  new AlertDialog.Builder( this ).setTitle( "No Internet Connection" ).setMessage( "Please check the connection & try again" ).setIcon( R.drawable.ic_action_report_problem ).setCancelable( false ).setPositiveButton( android.R.string.yes, new DialogInterface.OnClickListener()
                  {
                        public void onClick( DialogInterface dialog, int which )
                        {
                              finish();
                        }
                  } ).show();
            }

      }

      private void setActivityParams()
      {
            // remove title
            requestWindowFeature( Window.FEATURE_NO_TITLE );

            // make activity full screen
            getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
      }

      /**
       * check internet connection
       *
       * @return true if connected else false
       */
      public boolean isConnectedToInternet()
      {
            ConnectivityManager connectivity = ( ConnectivityManager ) getSystemService( Context.CONNECTIVITY_SERVICE );
            if ( connectivity != null )
            {
                  NetworkInfo[] info = connectivity.getAllNetworkInfo();
                  if ( info != null )
                  {
                        for ( int i = 0; i < info.length; i++ )
                        {
                              if ( info[ i ].getState() == NetworkInfo.State.CONNECTED )
                              {
                                    return true;
                              }
                        }
                  }
            }
            return false;
      }

      private void fetchDrugData()
      {
            final AsyncTask< Void, Void, String > populateFloorData;
            try
            {
                  populateFloorData = new AsyncTask< Void, Void, String >()
                  {
                        public String response;

                        @Override
                        protected String doInBackground( Void... args )
                        {
                              try
                              {
                                    response = CommonMethods.performGet( AppVariables.pharmEasyURL );
                                    JSONArray durgsDetail = new JSONObject( response ).getJSONArray( "result" );
                                    if ( durgsDetail != null )
                                    {
                                          populateDatabase( durgsDetail );
                                    }
                              }
                              catch ( Exception e )
                              {
                                    e.printStackTrace();
                              }
                              return response;
                        }

                        @Override
                        protected void onPostExecute( String result )
                        {
                              if ( result != null )
                              {
                                    openPagerScreen();
                              }
                              super.onPostExecute( result );
                        }
                  };
                  if ( Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB )
                  {
                        populateFloorData.execute();
                  }
                  else
                  {
                        populateFloorData.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
                  }
            }
            catch ( Exception ex )
            {

            }
      }

      private void openPagerScreen()
      {
            Intent intent = new Intent( this, MainActivity.class );
            startActivity( intent );

            // finish splash screen
            finish();
      }

      private void populateDatabase( JSONArray durgsDetail )
      {
            // get instance of databaseHandler
            DatabaseHelper db = MainApplication.getDatabaseHelper();

            for ( int i = 0; i < durgsDetail.length(); i++ )
            {
                  try
                  {
                        JSONObject currentDrugJson = durgsDetail.getJSONObject( i );

                        Drug drug = new Drug();
                        drug.setName( currentDrugJson.getString( "name" ) );
                        drug.setID( currentDrugJson.getString( "id" ) );
                        drug.setAvailable( currentDrugJson.getString( "available" ) );
                        drug.setPrice( currentDrugJson.getString( "mrp" ) );
                        drug.setManufacturer( currentDrugJson.getString( "manufacturer" ) );
                        drug.setForm( currentDrugJson.getString( "form" ) );

                        // add to sqllite database
                        db.addDrug( drug );

                  }
                  catch ( JSONException e )
                  {
                        e.printStackTrace();
                  }
            }
      }
}
