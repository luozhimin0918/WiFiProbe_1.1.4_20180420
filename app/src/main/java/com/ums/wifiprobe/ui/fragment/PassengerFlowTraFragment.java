package com.ums.wifiprobe.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.ums.wifiprobe.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2015/11/30.
 */
public class PassengerFlowTraFragment extends Fragment implements OnChartValueSelectedListener {
    protected WeakReference<View> mRootView;
    @BindView(R.id.chart_bar_mulp)
    BarChart chartBarMulp;
    Unbinder unbinder;
    private View view;
    private final static String[] weekDays = new String[]{"12-01", "12-02", "12-03", "12-04", "12-05", "12-06", "12-07"};


    Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null || mRootView.get() == null) {
            view = inflater.inflate(R.layout.frament_pass_tra_main, null);
            mRootView = new WeakReference<View>(view);
            mContext = getContext();
            ButterKnife.bind(this, view);
            initChart();
            initData();
        } else {
            ViewGroup parent = (ViewGroup) mRootView.get().getParent();
            if (parent != null) {
                parent.removeView(mRootView.get());
            }
        }
        unbinder = ButterKnife.bind(this, mRootView.get());
        return mRootView.get();

    }

    private void initData() {
        int groupCount =7;
        int startYear = 1980;
        int endYear = startYear + groupCount;
        float groupSpace = 0.08f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.4f; // x4 DataSet

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        float randomMultiplier =6 * 10f;
        for (int i = startYear; i < endYear; i++) {
            yVals1.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
            yVals2.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
        }

        BarDataSet set1, set2, set3, set4;

        if (chartBarMulp.getData() != null && chartBarMulp.getData().getDataSetCount() > 0) {

            set1 = (BarDataSet) chartBarMulp.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) chartBarMulp.getData().getDataSetByIndex(1);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            chartBarMulp.getData().notifyDataChanged();
            chartBarMulp.notifyDataSetChanged();

        } else {
            // create 4 DataSets
            set1 = new BarDataSet(yVals1, "本周数据");
            set1.setColor(Color.rgb(73, 169, 238));
            set2 = new BarDataSet(yVals2, "上周数据");
            set2.setColor(Color.rgb(152, 216, 125));

            BarData data = new BarData(set1, set2);
            data.setValueFormatter(new LargeValueFormatter());
            data.setValueTypeface(Typeface.createFromAsset(getContext().getAssets(), "OpenSans-Light.ttf"));

            chartBarMulp.setData(data);
        }
        chartBarMulp.getBarData().setBarWidth(barWidth);

        // restrict the x-axis range
        chartBarMulp.getXAxis().setAxisMinimum(startYear);
        chartBarMulp.getBarData().setDrawValues(false);//是dataSet的属性，设置是否在图上显示出当前点（柱状图）的值

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        chartBarMulp.getXAxis().setAxisMaximum(startYear + chartBarMulp.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chartBarMulp.groupBars(startYear, groupSpace, barSpace);
        chartBarMulp.invalidate();
    }

    private void initChart() {
       /* chartBarMulp.setOnChartValueSelectedListener(this);
        chartBarMulp.setDrawBarShadow(false);
        chartBarMulp.setDrawValueAboveBar(true);
        chartBarMulp.setScaleEnabled(false);
        chartBarMulp.getDescription().setEnabled(false);
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chartBarMulp.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chartBarMulp.setPinchZoom(false);

        chartBarMulp.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);
        IAxisValueFormatter xAxisFormatter = new WeekAxisValueFormatter(weekDays);

        XAxis xAxis = chartBarMulp.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "OpenSans-Light.ttf"));
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);
        xAxis.setTextColor(Color.parseColor("#727272"));
//        xAxis.setTextSize(18f);

//        xAxis.setYOffset(10f);
//        xAxis.setXOffset(10f);

        YAxis leftAxis = chartBarMulp.getAxisLeft();
        leftAxis.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "OpenSans-Light.ttf"));
        leftAxis.setLabelCount(6, true);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.enableGridDashedLine(10f, 10f, 0f);

        YAxis rightAxis = chartBarMulp.getAxisRight();
        rightAxis.setEnabled(false);


        Legend l = chartBarMulp.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
        l.setEnabled(false);*/
        chartBarMulp.setOnChartValueSelectedListener(this);
        chartBarMulp.getDescription().setEnabled(false);

        chartBarMulp.setDrawBorders(false);

        // scaling can now only be done on x- and y-axis separately
        chartBarMulp.setPinchZoom(false);
        chartBarMulp.setScaleEnabled(false);
        chartBarMulp.setDragEnabled(true);
        chartBarMulp.setDrawBarShadow(false);

        chartBarMulp.setDrawGridBackground(false);

        Legend l = chartBarMulp.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "OpenSans-Light.ttf"));
        l.setYOffset(0f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        XAxis xAxis = chartBarMulp.getXAxis();
        xAxis.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "OpenSans-Light.ttf"));
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf((int) value);
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);// 是否绘制轴线
        YAxis leftAxis = chartBarMulp.getAxisLeft();
        leftAxis.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "OpenSans-Light.ttf"));
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        chartBarMulp.getAxisRight().setEnabled(false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
