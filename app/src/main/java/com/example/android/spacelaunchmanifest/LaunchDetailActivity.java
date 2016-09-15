package com.example.android.spacelaunchmanifest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Detailview contains fragments in tablayout
 * Created by aaron on 9/14/2016.
 */
public class LaunchDetailActivity extends AppCompatActivity
        implements AppBarLayout.OnOffsetChangedListener {

    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    private boolean mIsAvatarShown = true;

    private ImageView mProfileImage;
    private int mMaxScrollSize;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_coord_layout);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.detailpage_tabs);
        ViewPager viewPager  = (ViewPager) findViewById(R.id.detailpage_viewpager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.detailpage_appbar);
        mProfileImage = (ImageView) findViewById(R.id.detailpage_profile_image);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detailpage_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        appbarLayout.addOnOffsetChangedListener(this);
        mMaxScrollSize = appbarLayout.getTotalScrollRange();


        Intent i = getIntent();
        LaunchItem currentLaunchItem = (LaunchItem) i.getSerializableExtra("currentLaunchItem");


        TextView mLaunchTitleTextView = (TextView) findViewById(R.id.detail_page_launch_title);
        mLaunchTitleTextView.setText(currentLaunchItem.getmLaunchName());

        TextView mLaunchPadLocationTextView = (TextView) findViewById(R.id.detail_page_launchpad_location);
        mLaunchPadLocationTextView.setText(currentLaunchItem.getmLaunchLocation());

        ImageView mDetailBigImageView = (ImageView) findViewById(R.id.detailpage_profile_backdrop);

        // get Launch preview picture url
        String currentImageURl = currentLaunchItem.getmRocketImageUrl();
        String smallerImageUrl = currentImageURl.replace("2560", "640");
        smallerImageUrl = smallerImageUrl.replace("1920", "640");

        Picasso.with(tabLayout.getContext()).load(smallerImageUrl).into(mDetailBigImageView);


        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), currentLaunchItem));
        tabLayout.setupWithViewPager(viewPager);

    }

    public static void start(Context c) {
        c.startActivity(new Intent(c, LaunchDetailActivity.class));
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;
            mProfileImage.animate().scaleY(0).scaleX(0).setDuration(200).start();
        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;

            mProfileImage.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }
    }

}
