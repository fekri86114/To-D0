package fekri.com.ux

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fekri.com.R
import kotlinx.android.synthetic.main.item_recyclerview_todo.view.*
import java.text.SimpleDateFormat
import java.util.*

class TodoAdapter(private val list: List<TodoModel>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recyclerview_todo, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(list[position]) // passing the object of the list that made in the ToDoModel.kt
    }

    override fun getItemId(position: Int): Long {
        return list[position].id!!.toLong()
    }

    // view holder is present inside the recycler view
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(todoModel: TodoModel) {
            with(itemView) {
                val colors = resources.getIntArray(R.array.random_color)
                val randomColor = colors[Random().nextInt(colors.size)]
                view_color_tag.setBackgroundColor(randomColor)
                txtShowTitle.text = todoModel.title
                txtShowTask.text = todoModel.description
                txtShowCategory.text = todoModel.category
                updateTime(todoModel.time)
                updateDate(todoModel.date)

            }
        }

        private fun updateTime(time: Long) {
            //Mon, 5 Jan 2020
            val myformat = "h:mm a"
            val sdf = SimpleDateFormat(myformat)
            itemView.txtShowTime.text = sdf.format(Date(time))

        }

        private fun updateDate(time: Long) {
            //Mon, 5 Jan 2020
            val myformat = "EEE, d MMM yyyy"
            val sdf = SimpleDateFormat(myformat)
            itemView.txtShowDate.text = sdf.format(Date(time))

        }
    }

}
