// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.chat.presentation.ui.chat.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.azure.android.communication.ui.chat.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
internal fun AcsChatSendMessageButton(
    contentDescription: String,
    onClick: () -> Unit = {}
) {
    val semantics = Modifier.semantics {
        this.contentDescription = contentDescription
        this.role = Role.Image
    }
    val painter =
        painterResource(id = R.drawable.azure_communication_ui_chat_ic_fluent_send_message_button_20_filled)
    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(2.25.dp)
            .size(19.5.dp, 19.5.dp)
            .clickable { onClick }
            .then(semantics)
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewAcsChatSendMessageButton() {
    AcsChatSendMessageButton(contentDescription = "Send Message Button")
}