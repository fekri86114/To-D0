package fekri.com.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import fekri.com.R
import fekri.com.databinding.ActivityTaskBinding
import fekri.com.ux.AppDatabase
import fekri.com.ux.TodoModel
import kotlinx.android.synthetic.main.activity_task.*
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.Calendar

const val DATABASE_NAME = "to_do.db"

class TaskActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var calendar: Calendar
    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    private lateinit var timeSetListener: TimePickerDialog.OnTimeSetListener
    private lateinit var binding: ActivityTaskBinding

    private var finalDate = 0L
    private var finalTime = 0L

    private val labels = arrayListOf("Personal", "Business", "Insurance", "Shopping", "Banking")

    private val database by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dateEdt.setOnClickListener(this)
        binding.timeEdt.setOnClickListener(this)
        binding.saveBtn.setOnClickListener(this)

        setUpSpinner()

    }

    private fun setUpSpinner() {
        val adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, labels)

        labels.sort()

        spinnerCategory.adapter = adapter
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.dateEdt -> {
                setListener()
            }
            R.id.timeEdt -> {
                setTimeListener()
            }
            R.id.saveBtn -> {
                saveTodo()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun saveTodo() {
        val category = spinnerCategory.selectedItem.toString()
        val title = titleInpLay.editText?.text.toString()
        val description = taskInpLay.editText?.text.toString()

        GlobalScope.launch(Dispatchers.Main) {
            val id = withContext(Dispatchers.IO) {
                return@withContext database.todoDao().insertTask(
                    TodoModel(
                        title = title,
                        description = description,
                        category = category,
                        date = finalDate,
                        time = finalTime
                    )
                )
            }
            finish()
        }
    }

    private fun setTimeListener() {

        calendar = Calendar.getInstance()

        timeSetListener =
            TimePickerDialog.OnTimeSetListener() { _: TimePicker, hourOfDay: Int, min: Int ->

                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, min)
                updateTime()

            }

        /*val  timePickerDialog =  */TimePickerDialog(
            this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE), false
        )
            .show()

    }

    @SuppressLint("SimpleDateFormat")
    private fun updateTime() {
        val myFormat = "h:mm a"
        val sdf = SimpleDateFormat(myFormat)
        finalTime = calendar.time.time
        timeEdt.setText(sdf.format(calendar.time))
    }

    private fun setListener() {

        calendar = Calendar.getInstance()

        dateSetListener =
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->

                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDate()

            }

        val datePickerDialog = DatePickerDialog(
            this, dateSetListener, calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()

    }

    @SuppressLint("SimpleDateFormat")
    private fun updateDate() {

        val myFormat = "EEE, d MM yyyy"
        val sdf = SimpleDateFormat(myFormat)
        finalDate = calendar.time.time
        dateEdt.setText(sdf.format(calendar.time))

        timeInptLay.visibility = View.VISIBLE

    }
}