package com.ums.wifiprobe.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.ums.wifiprobe.R;
import com.ums.wifiprobe.ui.activity.RevisedTurnoverActivity;
import com.ums.wifiprobe.ui.customview.EasyDialog;
import com.ums.wifiprobe.utils.BarChartManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2015/11/30.
 */
public class PassengerFlowTraFragment extends Fragment implements OnChartValueSelectedListener {
    protected WeakReference<View> mRootView;
    @BindView(R.id.chart_bar_mulp)
    BarChart chartBarMulp;
    Unbinder unbinder;
    @BindView(R.id.chang_Tari_button)
    Button changTariButton;
    @BindView(R.id.easy_mess_ke_num)
    LinearLayout easyMessKeNum;
    @BindView(R.id.question_mark_Keliu_num)
    ImageView question_mark_Keliu_num;
    @BindView(R.id.radioToday)
    RadioButton radioToday;
    @BindView(R.id.radioWeek)
    RadioButton radioWeek;
    @BindView(R.id.radioMonth)
    RadioButton radioMonth;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
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
            initData();
            initChart();
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

    }

    private void initChart() {
        BarChartManager barChartManager2 = new BarChartManager(chartBarMulp, getContext());

        //设置x轴的数据
        List<String> xValues0 = new ArrayList<>();
        xValues0.add("12-01");
        xValues0.add("12-02");
        xValues0.add("12-03");
        xValues0.add("12-04");
        xValues0.add("12-05");
        xValues0.add("12-06");
        xValues0.add("12-07");


        //设置y轴的数据()
        List<List<Float>> yValues = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Float> yValue = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                yValue.add((float) (Math.random() * 8) + 2);
            }
            yValues.add(yValue);
        }


        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("本周数据");
        names.add("上周数据");
        //创建多条柱状的图表
        barChartManager2.showBarChart(xValues0, yValues, names);


       /* chartBarMulp.setOnChartValueSelectedListener(this);
        chartBarMulp.getDescription().setEnabled(false);

        chartBarMulp.setDrawBorders(false);

        // scaling can now only be done on x- and y-axis separately
        chartBarMulp.setPinchZoom(false);
        chartBarMulp.setScaleEnabled(false);
        chartBarMulp.setDragEnabled(true);
        chartBarMulp.setDrawBarShadow(false);

        chartBarMulp.setDrawGridBackground(false);
        //设置动画效果
        chartBarMulp.animateY(500, Easing.EasingOption.Linear);
        chartBarMulp.animateX(500, Easing.EasingOption.Linear);
        Legend l = chartBarMulp.getLegend();
        l.setForm(Legend.LegendForm.SQUARE);//图示 标签的形状。   正方形
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "OpenSans-Light.ttf"));
        l.setYOffset(10f);
        l.setXOffset(0f);
        l.setYEntrySpace(20f);
        l.setTextSize(10f);
        l.setFormSize(12);

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
        xAxis.setDrawGridLines(false);//设置竖状的线是否显示
        xAxis.setAxisMinimum(0f);
        YAxis leftAxis = chartBarMulp.getAxisLeft();
        leftAxis.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "OpenSans-Light.ttf"));
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setDrawAxisLine(false);// 是否绘制轴线
        chartBarMulp.getAxisRight().setEnabled(false);*/



        /* int groupCount = 7;
        int startYear = 1980;
        int endYear = startYear + groupCount;
        float groupSpace = 0.08f;
        float barSpace = 0.03f; // x4 DataSet
        float barWidth = 0.4f; // x4 DataSet

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        float randomMultiplier = 6 * 10f;
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
        chartBarMulp.invalidate();*/
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

    @OnClick({R.id.chang_Tari_button, R.id.easy_mess_ke_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chang_Tari_button:
                Intent intent = new Intent(getContext(), RevisedTurnoverActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_anim3, R.anim.activity_out1);
                break;
            case R.id.easy_mess_ke_num:
                new EasyDialog(getContext())
                        .setLayoutResourceId(R.layout.layout_tip_text)
                        .setBackgroundColor(getContext().getResources().getColor(R.color.background_color_black))
                        .setLocationByAttachedView(question_mark_Keliu_num)
                        .setGravity(EasyDialog.GRAVITY_BOTTOM)
                        .setAnimationAlphaShow(600, 0.0f, 1.0f)
                        .setAnimationAlphaDismiss(600, 1.0f, 0.0f)
                        .setTouchOutsideDismiss(true)
                        .setMatchParent(false)
                        .setMarginLeftAndRight(24, 24)
                        .setOutsideColor(getContext().getResources().getColor(R.color.outside_color_trans))
                        .show();
                break;
        }
    }
}
