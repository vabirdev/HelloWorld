package ir.vab.dev.helloworld.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.res.stringResource

@Composable
fun annotatedStringResource(@StringRes id: Int): AnnotatedString {
    val context = LocalContext.current
    val string = stringResource(id)
    return AnnotatedString.fromHtml(string)
}