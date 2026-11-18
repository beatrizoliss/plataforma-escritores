package com.example.etecliterariatcc;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import
        com.google.android.material.bottomnavigation.BottomNavigationView;
public class MainActivity3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main3 );
        BottomNavigationView bottomnav=findViewById(
                R.id.bottom_navigation);
        bottomnav.setOnNavigationItemSelectedListener( navListener );
        if (savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container,
                    new frag1() ).commit();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener
            navListener =
            new
                    BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected( MenuItem item) {
                            Fragment op = null;
                            if(item.getItemId()==R.id.op2) {
                                op = new frag1();
                            }
                            else
                            if(item.getItemId()==R.id.op3) {
                                op = new frag2();
                            }

                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,op).commit();
                            return true;
                        }
                    };
}