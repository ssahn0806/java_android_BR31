package com.example.baskinrobbins;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import androidx.core.content.res.ResourcesCompat;

import org.w3c.dom.Text;

import java.util.Random;

public class SecondActivity extends Activity {
    private RadioButton radioButtons[] = new RadioButton[6];
    private ImageView images[] = new ImageView[32];
    private int[] matchImg = {R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,R.drawable.i5,R.drawable.i6,R.drawable.i7,R.drawable.i8,R.drawable.i9,R.drawable.i10,R.drawable.i11,R.drawable.i12,R.drawable.i13,R.drawable.i14,R.drawable.i15,R.drawable.i16,R.drawable.i17,R.drawable.i8,R.drawable.i19,R.drawable.i20,R.drawable.i21,R.drawable.i22,R.drawable.i23,R.drawable.i24,R.drawable.i25,R.drawable.i26,R.drawable.i27,R.drawable.i28,R.drawable.i29,R.drawable.i30,R.drawable.i31,R.drawable.i32};
    private AutoCompleteTextView autoViews[] = new AutoCompleteTextView[6];
    private TextView Notices[] = new TextView[6];
    private ImageView finals[] = new ImageView[6];
    private static int size_check = -1;
    private static boolean input_check = false;
    private static boolean all_check = false;
    private static int status = 0;

    class BtnOnClickListener implements Button.OnClickListener{
        @Override
        public void onClick(View view) {
            final LinearLayout layout_info = (LinearLayout)findViewById(R.id.layout_info);
            final ViewFlipper viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper1);
            final Button prev = (Button)findViewById(R.id.btnPrev);
            final Button next = (Button)findViewById(R.id.btnNext);
            final ImageView img_selectk = (ImageView)findViewById(R.id.img_kind);
            final ImageView img_size = (ImageView)findViewById(R.id.final_img);
            final TextView txt_selectk = (TextView)findViewById(R.id.txt_kind);
            final TextView txt_count = (TextView)findViewById(R.id.txt_select);
            final TextView txt_price = (TextView)findViewById(R.id.final_price);
            final TextView txt_time = (TextView)findViewById(R.id.final_time);
            final TextView txt_size = (TextView)findViewById(R.id.final_size);
            final CheckBox[] checkBox = {(CheckBox)findViewById(R.id.chk1),(CheckBox)findViewById(R.id.chk2)};

            int inputs=0;
            int t=0;
            int[] imgs = {R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,R.drawable.c6};
            int[] price = {4000,6200,8200,15500,22000,26500};
            int[] size = {R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,R.drawable.s6};
            int[] finals = {R.id.final_i1,R.id.final_i2,R.id.final_i3,R.id.final_i4,R.id.final_i5,R.id.final_i6};
            int[] matches = {R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,R.drawable.i5,R.drawable.i6,R.drawable.i7,R.drawable.i8,R.drawable.i9,R.drawable.i10,R.drawable.i11,R.drawable.i12,R.drawable.i13,R.drawable.i14,R.drawable.i15,R.drawable.i16,R.drawable.i17,R.drawable.i8,R.drawable.i19,R.drawable.i20,R.drawable.i21,R.drawable.i22,R.drawable.i23,R.drawable.i24,R.drawable.i25,R.drawable.i26,R.drawable.i27,R.drawable.i28,R.drawable.i29,R.drawable.i30,R.drawable.i31,R.drawable.i32};
            String[] kinds = {"싱글킹","더블레귤러","파인트","쿼터","패밀리","하프갤런"};
            String[] counts = {"1가지맛 선택","2가지맛 선택","3가지맛 선택","4가지맛 선택","5가지맛 선택","6가지맛 선택"};
            String[] name = {"아이스 호떡","아이스 붕어빵","매시업스 시리얼","오레오 쿠키 앤 크림","메이플 월넛",
                    "망고 탱고","다크 초코 나이트","북극곰 폴라베어","에스프레소 앤 크림","아몬드 봉봉봉",
                    "엄마는 외계인","쫀떡궁합","베리 그래놀라","봉쥬르, 마카롱","마법사의 할로윈",
                    "민트 초콜릿 칩","슈팅스타","사랑에 빠진 딸기","초코나무 숲","뉴욕 치즈 케이크",
                    "피스타치오 아몬드","베리베리 스트로베리","바람과 함께 사라지다","레인보우 샤베트","자모카 아몬드 훠지",
                    "이상한 나라의 솜사탕","초콜릿","31요거트","그린티","체리쥬빌레",
                    "바닐라","초콜릿 무스"};
            ImageView final_img;

            switch (view.getId())
            {
                case R.id.btnPrev:
                    status--;
                    if(status==0)
                    {
                        viewFlipper.showPrevious();
                        prev.setEnabled(false);
                    }
                    else if (status > 0)
                    {
                        viewFlipper.showPrevious();
                        if(next.isEnabled()==false)
                            next.setEnabled(true);
                        if(prev.isEnabled()==false)
                            prev.setEnabled(true);
                    }
                    else
                    {
                        status = 0;
                    }
                    break;
                case R.id.btnNext:
                    if(size_check>-1)
                    {
                        status++;
                        if(status == 2)
                        {
                            inputs=0;
                            input_check=false;
                            for(int k=0;k<size_check+1;k++)
                            {
                                if(!autoViews[k].getText().toString().equals(""))
                                {
                                    inputs++;
                                }
                            }

                            if(inputs == size_check+1)
                                input_check = true;

                            if(input_check==true)
                            {
                                viewFlipper.showNext();
                                txt_price.setText(Integer.toString(price[size_check])+"원");
                                txt_size.setText(kinds[size_check]);
                                Random rand = new Random();
                                rand.setSeed(System.currentTimeMillis());
                                t = rand.nextInt(20)+10;
                                Intent outIntent = new Intent(getApplicationContext(),MainActivity.class);
                                outIntent.putExtra("Time",t);
                                setResult(RESULT_OK,outIntent);
                                txt_time.setText(Integer.toString(t)+"분");
                                img_size.setImageResource(size[size_check]);
                                for(int i=0;i<size_check+1;i++)
                                {
                                    final_img = (ImageView)findViewById(finals[i]);
                                    for(int j=0;j<name.length;j++)
                                    {
                                        if(autoViews[i].getText().toString().equals(name[j]))
                                        {
                                            final_img.setImageResource(matches[j]);
                                            break;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"맛을 모두 입력해주세요.",Toast.LENGTH_SHORT).show();
                                status--;
                            }

                        }
                        else if(status < 2)
                        {
                            viewFlipper.showNext();
                            img_selectk.setImageResource(imgs[size_check]);
                            txt_selectk.setText(kinds[size_check]);
                            txt_count.setText(counts[size_check]);
                            for(int k=0;k<6;k++)
                            {
                                if(k>size_check)
                                {
                                    Notices[k].setText("  선택불가  ");
                                    Notices[k].setBackgroundResource(R.drawable.btn_bg);
                                    autoViews[k].setVisibility(View.INVISIBLE);
                                    autoViews[k].setText("");
                                }
                                else
                                {
                                    Notices[k].setText("선택할 맛"+Integer.toString(k+1));
                                    Notices[k].setBackgroundResource(R.drawable.grid_bg);
                                    autoViews[k].setVisibility(View.VISIBLE);
                                    autoViews[k].setText("");
                                }
                            }
                            if(prev.isEnabled()==false)
                                prev.setEnabled(true);
                        }
                        else
                        {
                            all_check=false;
                            if(checkBox[0].isChecked() && checkBox[1].isChecked())
                                all_check=true;
                            // 다른 곳으로 이동
                            if(all_check==true)
                            {
                                status=0;
                                size_check=-1;
                                finish();
                            }
                            else
                            {
                                status=2;
                                Toast.makeText(getApplicationContext(),"체크하지 않은 항목이 있습니다.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"사이즈를 선택하세요.",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btnCenter:
                    break;
            }
        }
    }
    class RadioBtnOnClickListener implements  RadioButton.OnClickListener{
        @Override
        public void onClick(View view) {
            LinearLayout layout_info = (LinearLayout)findViewById(R.id.layout_info);
            ImageView img_size = (ImageView)findViewById(R.id.img_size);
            TextView txt_price = (TextView)findViewById(R.id.txt_price);
            TextView txt_volume = (TextView)findViewById(R.id.txt_volume);
            TextView txt_info = (TextView)findViewById(R.id.txt_info);

            int[] size = {R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,R.drawable.s6};
            int[] price = {4000,6200,8200,15500,22000,26500};
            int[] volume = {145,230,320,620,960,1200};
            String[] info = {"좋아하는 맛 한가지만\n듬뿍 맛볼수 있는 싱글콘",
                            "두가지 맛을 선택할 수 있는\n사이즈업 더블콘",
                            "세가지 맛을 골라 담은\n가성비 사이즈",
                            "4가지 맛을 골라 담기\n넉넉한 사이즈",
                            "5가지 맛을 골라\n가족과 나눠먹는 재미를",
                            "6가지 맛을 골라\n두고두고 먹는 즐거움을"};
            for(int i=0;i<6;i++)
            {
                if(view.getId() == radioButtons[i].getId())
                {
                    img_size.setImageResource(size[i]);
                    txt_price.setText(Integer.toString(price[i]));
                    txt_volume.setText(Integer.toString(volume[i]));
                    txt_info.setText(info[i]);
                    size_check = i;
                    break;
               }
            }
            if(layout_info.getVisibility()==View.INVISIBLE)
                layout_info.setVisibility(View.VISIBLE);
        }
    }

    class ImageOnClickListener implements ImageView.OnClickListener{
        TextView txt_name = (TextView)findViewById(R.id.txt_name);
        TextView txt_summary = (TextView)findViewById(R.id.txt_summary);
        ImageView select_img;
        String[] name = {"아이스 호떡","아이스 붕어빵","매시업스 시리얼","오레오 쿠키 앤 크림","메이플 월넛",
                         "망고 탱고","다크 초코 나이트","북극곰 폴라베어","에스프레소 앤 크림","아몬드 봉봉봉",
                         "엄마는 외계인","쫀떡궁합","베리 그래놀라","봉쥬르, 마카롱","마법사의 할로윈",
                         "민트 초콜릿 칩","슈팅스타","사랑에 빠진 딸기","초코나무 숲","뉴욕 치즈 케이크",
                         "피스타치오 아몬드","베리베리 스트로베리","바람과 함께 사라지다","레인보우 샤베트","자모카 아몬드 훠지",
                         "이상한 나라의 솜사탕","초콜릿","31요거트","그린티","체리쥬빌레",
                         "바닐라","초콜릿 무스"};
        String[] summary = {"달콤한 호떡 아이스크림에\n호떡 리본과 쫄깃한 떡, 씨앗이 어우러진 제품",
                            "달콤한 커스터드향 아이스크림과 팥 아이스크림\n그 속에 붕어빵 큐브와 팥 리본이 쏙쏙",
                            "콘푸러스트 아이스크림, 첵스 초코 아이스크림에\n달콤하게 초코 코팅한 첵스 초코 크럼블이 쏘옥!",
                            "부드러운 바닐라향 아이스크림에,\n달콤하고 진한 오레오 쿠키가 듬뿍!",
                            "고소하고 향긋한 호두 아이스크림에\n호두가 듬뿍 들어있는 제품",
                            "달콤상큼한 망고 시럽이 곁들여진 망고 아이스크림",
                            "초콜릿 프레첼과 함께 즐기는 진한 다크 초콜릿의 맛",
                            "폴라베어처럼 쿨~한 민트 아이스크림에 크런치가 쏙쏙!",
                            "유지방 1/2로 더욱 가볍게 즐기는 에스프레소 한잔",
                            "부드러운 바닐라향 아이스크림에 달콤한 초콜릿 리본과\n초코코팅 아몬드가 듬뿍 들어간 아이스크림",
                            "밀크 초콜릿,다크 초콜릿,화이트 무스 세가지 아이스크림에\n달콤 바삭한 초콜볼이 더해진 아이스크림",
                            "고소한 흑임자,인절미 아이스크림에\n쫄깃한 떡리본과 바삭한 프랄린 피칸이 쏙쏙!",
                            "마스카포네 치즈 아이스크림에\n라즈베리와 그래놀라가 쏘옥!",
                            "부드러운 크림치즈 아이스크림과\n마카롱, 초콜릿의 달콤한 만남!",
                            "초콜릿과 민트향 아이스크림속에\n마법같은 팝핑캔디가 톡톡!",
                            "쿨한 그녀들의 선택! 상쾌한 민트향에 초코칩까지!",
                            "톡톡 터지는 팝핑 캔디와 스프링클 캔디\n상큼한 체리 시럽이 들어있는 제품",
                            "크런치 초콜릿, 치즈 케이크, 스트로베리가\n듬뿍 들어있는 아이스크림",
                            "2014년 아이스크림 콘테스트 1위 선정작!",
                            "부드럽게 즐기는 뉴욕식 정통 치즈케이크 아이스크림",
                            "피스타치오향과 아몬드가 만나 고소함이 두배!",
                            "새콤상큼 딸기 과육이 듬뿍!",
                            "블루베리와 딸기로 상큼함을 더한 치즈케이크 한 조각",
                            "상큼한 파인애플,오렌지,라즈베리 샤베트",
                            "깊고 풍부한 자모카 아이스크림에 고소한 아몬드와\n초콜릿 훠지 시럽이 들어있는 제품",
                            "부드럽고 달콤한 솜사탕과\n함께 떠나는 이상한 나라로의 여행",
                            "진하고 부드러운 정통 초콜릿 아이스크림",
                            "유산균이 살아 있는 오리지널 요거트 아이스크림",
                            "엄선된 녹차를 사용한 싱그러운 그린티 아이스크림",
                            "체리과육이 탱글탱글 씹히는 체리 아이스크림",
                            "부드럽고 깔끔한 바닐라 아이스크림",
                            "초콜릿 칩이 들어있는 진한 초콜릿 아이스크림"};
        @Override
        public void onClick(View view) {
            for(int i=0;i<32;i++)
            {
                if(view.getId() == images[i].getId())
                {
                    for(int j=0;j<32;j++)
                    {
                        if(i!=j) {
                            select_img = (ImageView) findViewById(images[j].getId());
                            select_img.setBackground(null);
                        }
                    }
                    Resources res = getResources();
                    Drawable img = ResourcesCompat.getDrawable(res,R.drawable.ice_bg,null);
                    select_img = (ImageView)findViewById(images[i].getId());
                    select_img.setBackground(img);
                    txt_name.setText(name[i]);
                    txt_summary.setText(summary[i]);
                    break;
                }
            }
        }
    }

    class NoticenameListener implements ImageView.OnClickListener{
        AutoCompleteTextView find_txt;
        @Override
        public void onClick(View view) {
            for(int i=0;i<finals.length;i++)
            {
                if(view.getId() == finals[i].getId())
                {
                    find_txt = autoViews[i];
                    if(!find_txt.getText().toString().equals(""))
                    {
                        Toast.makeText(getApplicationContext(),find_txt.getText().toString(),Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final String items[] = {"아이스 호떡","아이스 붕어빵","매시업스 시리얼","오레오 쿠키 앤 크림","메이플 월넛",
                "망고 탱고","다크 초코 나이트","북극곰 폴라베어","에스프레소 앤 크림","아몬드 봉봉봉",
                "엄마는 외계인","쫀떡궁합","베리 그래놀라","봉쥬르, 마카롱","마법사의 할로윈",
                "민트 초콜릿 칩","슈팅스타","사랑에 빠진 딸기","초코나무 숲","뉴욕 치즈 케이크",
                "피스타치오 아몬드","베리베리 스트로베리","바람과 함께 사라지다","레인보우 샤베트","자모카 아몬드 훠지",
                "이상한 나라의 솜사탕","초콜릿","31요거트","그린티","체리쥬빌레",
                "바닐라","초콜릿 무스"};
        BtnOnClickListener onClickListener = new BtnOnClickListener();

        Button btnPrev = (Button)findViewById(R.id.btnPrev);
        btnPrev.setOnClickListener(onClickListener);
        Button btnNext = (Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(onClickListener);
        Button btnHelp = (Button)findViewById(R.id.btnCenter);
        btnHelp.setOnClickListener(onClickListener);

        RadioBtnOnClickListener rdoClickListener = new RadioBtnOnClickListener();
        int[] rdoId = {R.id.rdo_size1,R.id.rdo_size2,R.id.rdo_size3,R.id.rdo_size4,R.id.rdo_size5,R.id.rdo_size6};
        for(int i=0;i<radioButtons.length;i++)
        {
            radioButtons[i] = (RadioButton)findViewById(rdoId[i]);
            radioButtons[i].setOnClickListener(rdoClickListener);
        }

        ImageOnClickListener imgClickListener = new ImageOnClickListener();
        int[] imgId = {R.id.img_i1,R.id.img_i2,R.id.img_i3,R.id.img_i4,R.id.img_i5,R.id.img_i6,R.id.img_i7,R.id.img_i8,R.id.img_i9,R.id.img_i10,R.id.img_i11,R.id.img_i12,R.id.img_i13,R.id.img_i14,R.id.img_i15,R.id.img_i16,R.id.img_i17,R.id.img_i18,R.id.img_i19,R.id.img_i20,R.id.img_i21,R.id.img_i22,R.id.img_i23,R.id.img_i24,R.id.img_i25,R.id.img_i26,R.id.img_i27,R.id.img_i28,R.id.img_i29,R.id.img_i30,R.id.img_i31,R.id.img_i32};
        for(int i=0;i<images.length;i++)
        {
            images[i] = (ImageView)findViewById(imgId[i]);
            images[i].setOnClickListener(imgClickListener);
        }

        final int[] autoId = {R.id.edit_select1,R.id.edit_select2,R.id.edit_select3,R.id.edit_select4,R.id.edit_select5,R.id.edit_select6};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,items);
        for(int i=0;i<autoViews.length;i++)
        {
            autoViews[i] = (AutoCompleteTextView)findViewById(autoId[i]);
            autoViews[i].setAdapter(adapter);
        }

        final int[] notyId = {R.id.txt_select1,R.id.txt_select2,R.id.txt_select3,R.id.txt_select4,R.id.txt_select5,R.id.txt_select6};
        for(int i=0;i<Notices.length;i++)
        {
            Notices[i] = (TextView)findViewById(notyId[i]);
        }

        NoticenameListener noticenameListener = new NoticenameListener();
        final int[] finalimgId = {R.id.final_i1,R.id.final_i2,R.id.final_i3,R.id.final_i4,R.id.final_i5,R.id.final_i6};
        for(int i=0;i<finals.length;i++)
        {
            finals[i] = (ImageView)findViewById(finalimgId[i]);
            finals[i].setOnClickListener(noticenameListener);
        }
    }
}
