package com.najimaddinova.moviesbyinteraktifkredi.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.tabs.TabLayout;
import com.najimaddinova.moviesbyinteraktifkredi.Adapter.MovieListRecyclerViewAdapter;
import com.najimaddinova.moviesbyinteraktifkredi.Adapter.TabAdapter;
import com.najimaddinova.moviesbyinteraktifkredi.Fragment.FragmentNowPlaying;
import com.najimaddinova.moviesbyinteraktifkredi.Fragment.FragmentSearch;
import com.najimaddinova.moviesbyinteraktifkredi.Fragment.FragmentTopRated;
import com.najimaddinova.moviesbyinteraktifkredi.Fragment.FragmentUpcoming;
import com.najimaddinova.moviesbyinteraktifkredi.Model.MovieSummaryModel;
import com.najimaddinova.moviesbyinteraktifkredi.R;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private SearchManager searchManager;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter adapter;
    private MovieListRecyclerViewAdapter movieAdapter;
    private MenuItem myActionMenuItem;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPages);
        tabLayout = findViewById(R.id.tabLayoutId);
        tabLayout.setupWithViewPager(viewPager);

        adapter = new TabAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new FragmentTopRated(), "Top Rated");
        adapter.addFragment(new FragmentUpcoming(), "Upcoming");
        adapter.addFragment(new FragmentNowPlaying(), "Now Playing");
        adapter.addFragment(new FragmentSearch(), "Search Movie");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_list, menu);
         myActionMenuItem = menu.findItem(R.id.search);
        // Get the SearchView and set the searchable configuration
        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("query", query);
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,"Search query");
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                movieAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}
