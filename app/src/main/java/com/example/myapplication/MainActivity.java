package com.example.myapplication;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Views
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigation;
    private LinearLayout layoutCalculator;
    private LinearLayout layoutAbout;
    
    // Calculator Views
    private TextInputLayout layoutInputWeight;
    private TextInputLayout layoutInputPrice;
    private EditText inputWeight;
    private EditText inputPrice;
    private RadioGroup radioGroupCategory;
    private RadioButton radioKeep;
    private RadioButton radioWear;
    private MaterialButton btnCalculate;
    private MaterialButton btnClear;
    
    // Results Views
    private MaterialCardView cardResults;
    private TextView txtTotalValue;
    private TextView txtPayableWeight;
    private TextView txtPayableValue;
    private TextView txtTotalZakat;
    private TextView txtThresholdNotice;
    
    // About Views
    private TextView btnGithubLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        // Setup edge-to-edge padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        initViews();
        
        // Setup toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }

        // Setup bottom navigation listener
        setupNavigation();

        // Setup calculator listeners
        setupCalculator();

        // Setup about page links
        setupAboutPage();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        layoutCalculator = findViewById(R.id.layout_calculator);
        layoutAbout = findViewById(R.id.layout_about);

        layoutInputWeight = findViewById(R.id.layout_input_weight);
        layoutInputPrice = findViewById(R.id.layout_input_price);
        inputWeight = findViewById(R.id.input_weight);
        inputPrice = findViewById(R.id.input_price);
        radioGroupCategory = findViewById(R.id.radio_group_category);
        radioKeep = findViewById(R.id.radio_keep);
        radioWear = findViewById(R.id.radio_wear);
        btnCalculate = findViewById(R.id.btn_calculate);
        btnClear = findViewById(R.id.btn_clear);

        cardResults = findViewById(R.id.card_results);
        txtTotalValue = findViewById(R.id.txt_total_value);
        txtPayableWeight = findViewById(R.id.txt_payable_weight);
        txtPayableValue = findViewById(R.id.txt_payable_value);
        txtTotalZakat = findViewById(R.id.txt_total_zakat);
        txtThresholdNotice = findViewById(R.id.txt_threshold_notice);

        btnGithubLink = findViewById(R.id.btn_github_link);
    }

    private void setupNavigation() {
        bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_calculator) {
                layoutCalculator.setVisibility(View.VISIBLE);
                layoutAbout.setVisibility(View.GONE);
                return true;
            } else if (itemId == R.id.nav_about) {
                layoutCalculator.setVisibility(View.GONE);
                layoutAbout.setVisibility(View.VISIBLE);
                return true;
            }
            return false;
        });
    }

    private void setupCalculator() {
        btnCalculate.setOnClickListener(v -> calculateZakat());
        btnClear.setOnClickListener(v -> clearFields());
    }

    private void calculateZakat() {
        // Reset errors
        layoutInputWeight.setError(null);
        layoutInputPrice.setError(null);

        String weightStr = inputWeight.getText().toString().trim();
        String priceStr = inputPrice.getText().toString().trim();

        // Validate weight input
        if (TextUtils.isEmpty(weightStr)) {
            layoutInputWeight.setError(getString(R.string.error_empty_weight));
            inputWeight.requestFocus();
            return;
        }

        double weight;
        try {
            weight = Double.parseDouble(weightStr);
            if (weight <= 0) {
                layoutInputWeight.setError(getString(R.string.error_invalid_weight));
                inputWeight.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            layoutInputWeight.setError(getString(R.string.error_invalid_weight));
            inputWeight.requestFocus();
            return;
        }

        // Validate price input
        if (TextUtils.isEmpty(priceStr)) {
            layoutInputPrice.setError(getString(R.string.error_empty_price));
            inputPrice.requestFocus();
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
            if (price <= 0) {
                layoutInputPrice.setError(getString(R.string.error_invalid_price));
                inputPrice.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            layoutInputPrice.setError(getString(R.string.error_invalid_price));
            inputPrice.requestFocus();
            return;
        }

        // Determine threshold value based on selected category
        int threshold = 85; // Default for Keep
        if (radioWear.isChecked()) {
            threshold = 200;
        }

        // Perform calculations
        double totalGoldValue = weight * price;
        double payableWeight = Math.max(0.0, weight - threshold);
        double payableValue = payableWeight * price;
        double totalZakat = payableValue * 0.025; // 2.5%

        // Format and display outputs
        txtTotalValue.setText(String.format(Locale.getDefault(), "RM %.2f", totalGoldValue));
        txtPayableWeight.setText(String.format(Locale.getDefault(), "%.2f g", payableWeight));
        txtPayableValue.setText(String.format(Locale.getDefault(), "RM %.2f", payableValue));
        txtTotalZakat.setText(String.format(Locale.getDefault(), "RM %.2f", totalZakat));

        // Display notice if weight is below the threshold
        if (weight <= threshold) {
            txtThresholdNotice.setText(getString(R.string.notice_not_payable, threshold));
            txtThresholdNotice.setVisibility(View.VISIBLE);
        } else {
            txtThresholdNotice.setVisibility(View.GONE);
        }

        // Show results card
        cardResults.setVisibility(View.VISIBLE);
    }

    private void clearFields() {
        inputWeight.setText("");
        inputPrice.setText("");
        layoutInputWeight.setError(null);
        layoutInputPrice.setError(null);
        cardResults.setVisibility(View.GONE);
        inputWeight.requestFocus();
    }

    private void setupAboutPage() {
        // Add underline programmatically to the clickable link
        btnGithubLink.setPaintFlags(btnGithubLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        btnGithubLink.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.github_url)));
            try {
                startActivity(browserIntent);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Unable to open browser", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            shareAppInfo();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareAppInfo() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "ZGold Danny - Gold Zakat Calculator");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Calculate your gold zakat with ZGold Danny! Check the source code and info on GitHub: " + getString(R.string.github_url));
        try {
            startActivity(Intent.createChooser(shareIntent, "Share app link using"));
        } catch (Exception e) {
            Toast.makeText(this, "Sharing not available", Toast.LENGTH_SHORT).show();
        }
    }
}