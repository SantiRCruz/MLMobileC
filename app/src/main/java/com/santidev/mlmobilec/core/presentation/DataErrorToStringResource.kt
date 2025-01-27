package com.santidev.mlmobilec.core.presentation

import com.santidev.mlmobilec.R
import com.santidev.mlmobilec.core.domain.util.DataError


fun DataError.toUiText(): UiText {
    val stringRes = when (this) {
        DataError.Remote.REQUEST_TIMEOUT -> R.string.error_request_timeout
        DataError.Remote.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        DataError.Remote.NO_INTERNET -> R.string.error_no_internet
        DataError.Remote.SERVER -> R.string.error_unknown
        DataError.Remote.SERIALIZATION -> R.string.error_serialization
        DataError.Remote.UNKNOWN -> R.string.error_unknown
    }

    return UiText.StringResourceId(stringRes)
}