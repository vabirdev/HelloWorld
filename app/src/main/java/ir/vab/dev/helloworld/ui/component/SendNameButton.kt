package ir.vab.dev.helloworld.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SubdirectoryArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ir.vab.dev.helloworld.R

@Composable
fun SendNameButton(
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val btnColors = IconButtonDefaults.iconButtonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
    )

        IconButton(
            onClick = onClick,
            enabled = enabled,
            colors = btnColors,
            shape = RoundedCornerShape(12.dp),
            modifier = modifier
        ) {
            Icon(
                imageVector = Icons.Filled.SubdirectoryArrowLeft,
                contentDescription = stringResource(R.string.send) // Avoid hardcoded strings
            )
        }
}