package com.project.eldalell.eldalell_delivery;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
//import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.Collection;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    BarChart barChart;
    PieChart pieChart;
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
    }



    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    CircleImageView profile_image;
    TextView tvName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        tvName = v.findViewById(R.id.tvName);
        profile_image = v.findViewById(R.id.profile_image);
        barChart = (BarChart) v.findViewById(R.id.barchart);
        if (MainActivity.delivery != null){
            Glide.with(getContext())
                    .load(MainActivity.delivery.getImage())
                    .centerCrop()
                    .placeholder(R.drawable.kan)
                    .into(profile_image);
            tvName.setText("Welcome "+MainActivity.delivery.getFirst_name());
        }

        //BarChart>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(100);
        barChart.animateXY(2000, 2000);
        barChart.invalidate();
        barChart.setDrawGridBackground(true);
        barChart.setClickable(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getDescription().setEnabled(false);

        //ToDo DataBase barChar
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 31f));
        barEntries.add(new BarEntry(2, 30f));
        barEntries.add(new BarEntry(3, 20f));
        barEntries.add(new BarEntry(4, 15f));
        barEntries.add(new BarEntry(5, 15f));
        barEntries.add(new BarEntry(6, 5f));
        barEntries.add(new BarEntry(7, 17f));
        barEntries.add(new BarEntry(8, 20f));
        barEntries.add(new BarEntry(9, 30f));
        barEntries.add(new BarEntry(10f, 12f));
        barEntries.add(new BarEntry(11, 20f));
        barEntries.add(new BarEntry(12f, 18f));


        BarDataSet barDataSet = new BarDataSet(barEntries, "Work");
        barDataSet.setColors(ColorTemplate.LIBERTY_COLORS);


        BarData data = new BarData(barDataSet);

        data.setHighlightEnabled(true);


        barChart.setData(data);

        data.setBarWidth(0.9f);


        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLUE);

        barChart.invalidate();


        String[] months = new String[]{"", "Jan  ", "Feb  ", "Mar  ", "Apr  ", "May  ", "Jun  ", "Jul  ", "Aug  ", "sept  ", "Oct  ", "Nov  ", "Des  "};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(months));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawLabels(true);
        xAxis.setLabelCount(barEntries.size());
        xAxis.isDrawLabelsEnabled();
        xAxis.setGranularity(1f);
        xAxis.setAxisLineWidth(8f);
        xAxis.setCenterAxisLabels(false);

        xAxis.setTextSize(12f);


//PieChart>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


        pieChart = (PieChart) v.findViewById(R.id.piechar);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        ArrayList<PieEntry> yValues = new ArrayList<>();
        //ToDo DataBase pieChar
        yValues.add(new PieEntry(50f, "Your Deliveries"));
        yValues.add(new PieEntry(50f, "All Deliveries"));


        PieDataSet dataSet = new PieDataSet(yValues, " ");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData data1 = new PieData((dataSet));
        data1.setValueTextSize(25f);
        data1.setValueTextColor(Color.BLACK);
        pieChart.setData(data1);


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public class MyXAxisValueFormatter implements IAxisValueFormatter {
        private String[] mValues = new String[]{};
        private int mValueCount = 0;

        /**
         * An empty constructor.
         * Use `setValues` to set the axis labels.
         */
        public MyXAxisValueFormatter() {
        }

        /**
         * Constructor that specifies axis labels.
         *
         * @param values The values string array
         */
        public MyXAxisValueFormatter(String[] values) {
            if (values != null)
                setValues(values);
        }

        /**
         * Constructor that specifies axis labels.
         *
         * @param values The values string array
         */
        public MyXAxisValueFormatter(Collection<String> values) {
            if (values != null)
                setValues(values.toArray(new String[values.size()]));
        }

        @Override
        public String getFormattedValue(float value, AxisBase axisBase) {
            int index = Math.round(value);

            if (index < 0 || index >= mValueCount || index != (int) value)
                return "";

            return mValues[index];
        }

        public String[] getValues() {
            return mValues;
        }

        public void setValues(String[] values) {
            if (values == null)
                values = new String[]{};

            this.mValues = values;
            this.mValueCount = values.length;
        }
    }
}
