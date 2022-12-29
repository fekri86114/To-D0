package fekri.com.ux

import androidx.room.Entity
import androidx.room.PrimaryKey

// make list for each activities

@Entity(tableName = "todo_model_table")
data class TodoModel(

    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,

    var title:String,
    var description:String,
    var category: String,
    var date:Long,
    var time:Long,
    var isFinished : Int = 0
)