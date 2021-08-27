package dev.bluefalcon

import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.*
import platform.posix.memcpy

fun NSData.string(): String? {
    return NSString.create(this, NSUTF8StringEncoding) as String?
}

fun NSData.toByteArray() = ByteArray(length.toInt()).apply {
    usePinned {
        memcpy(it.addressOf(0), bytes, length)
    }
}