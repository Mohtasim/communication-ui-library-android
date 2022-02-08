// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.configuration

import com.azure.android.communication.ui.configuration.events.CallCompositeEventsHandler
import java.lang.RuntimeException
import java.lang.ref.WeakReference

internal class CallCompositeConfiguration {
    var themeConfig: ThemeConfiguration? = null
    var callCompositeEventsHandler = CallCompositeEventsHandler()
    var callConfig: CallConfiguration? = null
    var localeConfiguration: LocaleConfiguration? = null

    /*
    CallCompositeConfiguration Storage

    The configuration for the call requires callbacks, but these callbacks
    can not be passed via intent (not primitive/serializable data).

    This is a storage container for Configuration objects, it uses a weak reference
    to prevent CallCompositeConfiguration from leaking Activities via it's callbacks.
     */
    companion object {
        private val configs: HashMap<Int, WeakReference<CallCompositeConfiguration>> = HashMap()

        // Store a Config by Instance ID
        //
        // Pass a null configuration to explicitly remove an instance
        fun putConfig(id: Int, configuration: CallCompositeConfiguration?) {
            if (configuration == null) {
                configs.remove(id)
            } else {
                configs[id] = WeakReference(configuration)
            }
        }

        // Gets a config by it's ID
        // May return null if the Configuration becomes garbage collected
        fun getConfig(id: Int): CallCompositeConfiguration = configs[id]?.get()
            ?: throw RuntimeException(
                if (configs.containsKey(id))
                    "Config with ID: $id was likely disposed, this is possible if the launching Activity was destroyed." +
                        "Consider scoping your CallComposite class to an Application instead of Activity."
                else
                    "This ID is not valid, and no entry exists in the map. Please file a bug, this is an error in the composite"
            )
    }
}