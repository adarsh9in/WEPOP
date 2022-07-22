import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vepop.Product
import com.example.vepop.R

class CustomAdapter(private val productList: ArrayList<Product>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ViewHolder(itemView = view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem =productList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageProduct.setImageResource(currentItem.image)

        // sets the text to the textview from our itemHolder class
        holder.labelProduct.text = currentItem.label
        holder.priceProduct.text=currentItem.price

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return productList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageProduct: ImageView = itemView.findViewById(R.id.image)
        val labelProduct: TextView = itemView.findViewById(R.id.label)
        val priceProduct: TextView = itemView.findViewById(R.id.price)
    }
}
