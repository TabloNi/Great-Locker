package club.biblelocker.biblelocker.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Toast
import club.biblelocker.biblelocker.R
import club.biblelocker.biblelocker.Utils.AlarmParser
import kotlinx.android.synthetic.main.activity_alarm_set.*
import java.util.*

class AlarmSetActivity : AppCompatActivity() {

    var volume: Int = 0
    var vibrate: Boolean = false
    var repeat: Boolean = false

    var flag = BooleanArray(7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_set)

        seekVolume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Write code to perform some action when progress is changed.
                volume = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is started.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is stopped.
            }

        })




        swVibrate.setOnCheckedChangeListener { p0, p1 -> vibrate = p1 }

        swRepeat.setOnCheckedChangeListener { p0, p1 -> repeat = p1 }

        make_ok.setOnClickListener {
            val hour = Integer.valueOf(txtHour.text.toString())
            val min = Integer.valueOf(txtMin.text.toString())
            //volume, vibrate, repeat
            val calendar = Calendar.getInstance()
            //먼저 어느 요일에 울려줄지 분석한다.
            var days = AlarmParser().parseDays("0110011")

            if(days[calendar[Calendar.DAY_OF_WEEK]] == 1) { //오늘 요일에 울리는게 맞다면(아니면 검사할필요 없이 걍 바로 등록해도 됨
                if ((calendar[Calendar.HOUR_OF_DAY] < hour) or //시간이 이전이거나
                        ((calendar[Calendar.MINUTE] < min) and (calendar[Calendar.HOUR_OF_DAY] == hour))) //시간은 같은데 분이 작다 (시간이 작으면 위에서 걸러지고, 시간이 크면 분이 어떻든 관계없음)
                {
                    //이러면 오늘이 아닌 다음 울려줄 알람부터 설정해야 한다.
                }
                else
                {
                    //오늘부터 설정한다
                }
            }
            else
            {
                //다음 알람일부터 설정해 준다
            }

            //알람 설정 시각이 현재 시각보다 앞인지 뒤인지 따져서
            //이미 지난 시간이면 다음날
            //아직 안됐으면 오늘부터

            //요일별 설정을 가져와서 INTERVAL_DAY로 7일마다 반복되게(매주) 설정
            //이런 설정을 거기(Receiver)에도 해줘야함

            //최종적으로 정리해보자
            //알림을 매주 똑같은 시간을 알람 설정하려면
            //매 요일을 분석해서
            //현재 시간보다 앞인지 뒤인지 판단하고
            //알람을 설정하고
            //그 다음, 인터벌을 7일로 줘서 울려준다.

            //공휴일을 제외한다는 옵션은 없어서 다행이다




        }
    }
}

