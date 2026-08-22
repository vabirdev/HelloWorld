package ir.vab.dev.helloworld.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.vab.dev.helloworld.R
import ir.vab.dev.helloworld.ui.theme.NotoSans

@Composable
fun NameTextField(
    name: String,
    onNameChange: (String) -> Unit,
    onSendClick: () -> Unit,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
    submit: Boolean
) {
    val shape = remember { RoundedCornerShape(10.dp) }
    val surfaceShadow = remember {
        Shadow(
            color = Color.Black.copy(alpha = 0.05f),
            radius = 20.dp,
            spread = (-2).dp,
            offset = DpOffset(x = 0.dp, y = 4.dp)
        )
    }

    val surfaceShadowOff = remember {
        Shadow(
            color = Color.Black.copy(alpha = 0f),
            radius = 20.dp,
            spread = (-2).dp,
            offset = DpOffset(x = 0.dp, y = 4.dp)
        )
    }

    Surface(
        modifier = modifier
            .padding(vertical = 30.dp, horizontal = 24.dp)
            .fillMaxWidth()
            .dropShadow(
                shape = shape,
                shadow = if(submit) surfaceShadowOff else surfaceShadow
            )
            .clip(shape)
            .clickable(){
                onEditClick()
            },
        shape = shape,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 6.dp, top = 4.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = name,
                onValueChange = onNameChange,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unfocusedPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    focusedSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unfocusedSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
                modifier = Modifier.weight(1f),
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.name_hint),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = NotoSans
                        )
                    )
                },
                prefix = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                },

                suffix = {
                    if (submit) {

                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    } else {
                        null
                    }
                },
                textStyle = TextStyle(
                    color = if (submit) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = NotoSans
                ),
                enabled = !submit
            )

            if (!submit) {

                SendNameButton(
                    enabled = name.isNotEmpty(),
                    onClick = onSendClick,
                )
            }
        }
    }
}



