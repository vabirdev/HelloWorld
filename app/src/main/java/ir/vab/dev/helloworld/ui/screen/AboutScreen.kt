package ir.vab.dev.helloworld.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.vab.dev.helloworld.R

@Composable
fun AboutScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}){
    val context = LocalContext.current

    // اطلاعات مربوط به برنامه و برنامه نویس
    val appName = stringResource(R.string.app_name)
    val appVersion = stringResource(R.string.app_ver)
    val appDescription = stringResource(R.string.app_desc)

    val developerName = stringResource(R.string.dev_name)
    val developerEmail = stringResource(R.string.dev_mail)
    val telegramUsername = stringResource(R.string.dev_telegram) // آیدی تلگرام بدون @
    val rubikaUsername = stringResource(R.string.dev_rubika)     // آیدی روبیکا بدون @

    Scaffold() {
        paddingValues ->
        Surface(
            modifier = modifier.padding(paddingValues).fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                // ۱. لوگوی برنامه
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground), // تصویر لوگو را بگذارید
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // ۲. عنوان برنامه
                Text(
                    text = appName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(8.dp))

                // ۳. توضیحات برنامه
                Text(
                    text = appDescription,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                // ۴. کارت اطلاعات برنامه‌نویس
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // عکس برنامه‌نویس
                        Image(
                            painter = painterResource(id = R.drawable.vali), // عکس برنامه‌نویس
                            contentDescription = "Developer Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.secondaryContainer)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // نام برنامه‌نویس
                        Text(
                            text = developerName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = stringResource(R.string.dev_title),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // ایمیل
                        ContactItem(
                            icon = Icons.Rounded.Email,
                            title = "ارسال ایمیل",
                            value = developerEmail,
                            onClick = {
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = Uri.parse("mailto:$developerEmail")
                                }
                                context.startActivity(intent)
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // شبکه های اجتماعی (تلگرام و روبیکا)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            // آیکون تلگرام (آیکون مناسب را در drawable قرار دهید)
                            SocialMediaButton(
                                name = stringResource(R.string.telegram),
                                iconRes = R.drawable.telegram, // R.drawable.ic_telegram
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/$telegramUsername"))
                                    context.startActivity(intent)
                                }
                            )

                            // آیکون روبیکا (آیکون مناسب را در drawable قرار دهید)
                            SocialMediaButton(
                                name = stringResource(R.string.rubika),
                                iconRes = R.drawable.rubika, // R.drawable.ic_rubika
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://rubika.ir/$rubikaUsername"))
                                    context.startActivity(intent)
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.height(24.dp))

                // ۵. نسخه نرم‌افزار
                Text(
                    text = "نسخه $appVersion",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.outline
                )


            }
        }

        Box(modifier = Modifier.padding(top = 42.dp, start = 24.dp).fillMaxSize(),
            contentAlignment = Alignment.TopStart){
            IconButton(onBackClick) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back menu", tint = MaterialTheme.colorScheme.onBackground)
            }
        }
    }

}

@Composable
fun ContactItem(
    icon: ImageVector,
    title: String,
    value: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = value,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun SocialMediaButton(
    name: String,
    iconRes: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = name,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = name,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}