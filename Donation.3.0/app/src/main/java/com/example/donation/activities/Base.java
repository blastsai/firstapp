package com.example.donation.activities;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.donation.R;
import com.example.donation.models.Donation;

import java.util.ArrayList;
import java.util.List;

public class Base extends AppCompatActivity {
    public final int target= 10000;
    public int totalDonated = 0;
    public static List<Donation> donations = new ArrayList<Donation>();

    public boolean newDonation(Donation donation)
    {
        boolean targetAchieved = totalDonated > target;
        if (!targetAchieved)
        {
            donations.add(donation);
            totalDonated += donation.amount;
        }
        else
        {
            Toast toast = Toast.makeText(this, "Targer Exceeded", Toast.LENGTH_SHORT);
            toast.show();
        }
        return targetAchieved;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_donate, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu)
    {
        super.onPrepareOptionsMenu(menu);
        MenuItem report = menu.findItem(R.id.menuReport);
        MenuItem donate = menu.findItem(R.id.menuDonate);

        if(donations.isEmpty())
            report.setEnabled(false);
        else
            report.setEnabled(true);

        if(this instanceof Donate){
            donate.setVisible(false);
            if(!donations.isEmpty())
                report.setVisible(true);
        }
        else {
            report.setVisible(false);
            donate.setVisible(true);
        }

        return true;
    }

    public void settings(MenuItem item)
    {
        Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
    }

    public void report(MenuItem item)
    {
        startActivity(new Intent(this, Report.class));
    }

    public void donate(MenuItem item)
    {
        startActivity(new Intent(this, Donate.class));
    }
}
