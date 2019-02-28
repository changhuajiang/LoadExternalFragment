package com.changhua.myhostapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

import dalvik.system.DexClassLoader;
import example.test_interfaces.FragmentHolder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            Class<?> requiredClass = null;
            final String apkPath = getPackageManager().getApplicationInfo("com.changhua.mypluginapp",0).sourceDir;
            final File dexTemp = getDir("temp_folder", 0);
            final String fullName = "com.changhua.mypluginapp.PlugInFragment";
            boolean isLoaded = true;

            // Check if class loaded
            try {
                requiredClass = Class.forName(fullName);
            } catch(ClassNotFoundException e) {
                isLoaded = false;
            }

            if (!isLoaded) {
                final DexClassLoader classLoader = new DexClassLoader(apkPath,
                        dexTemp.getAbsolutePath(),
                        null,
                        getApplicationContext().getClassLoader());

                requiredClass = classLoader.loadClass(fullName);
            }

            if (null != requiredClass) {
                // Try to cast to required interface to ensure that it's can be cast
                final FragmentHolder holder = FragmentHolder.class.cast(requiredClass.newInstance());

                if (null != holder) {
                    final Fragment fragment = holder.getFragment();

                    if (null != fragment) {
                        final FragmentTransaction trans = getFragmentManager().beginTransaction();

                        trans.add(R.id.fragmentPlace, fragment, "MyFragment").commit();

                        
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
