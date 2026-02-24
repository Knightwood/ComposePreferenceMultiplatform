import androidx.compose.runtime.Composable

@Composable
fun BasicButton(text:String, onClick: () -> Unit){
    androidx.compose.material3.Button(onClick = onClick) {
        androidx.compose.material3.Text(text = text)
    }
}
