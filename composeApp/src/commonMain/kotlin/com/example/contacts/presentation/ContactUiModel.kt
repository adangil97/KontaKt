package com.example.contacts.presentation

import com.example.notes.presentation.NoteUiModel

data class ContactUiModel(
    val id: Long = 0,
    val name: String = "",
    val lastName: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val photo: ByteArray? = null,
    val notes: List<NoteUiModel>? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ContactUiModel

        if (id != other.id) return false
        if (name != other.name) return false
        if (lastName != other.lastName) return false
        if (phoneNumber != other.phoneNumber) return false
        if (email != other.email) return false
        if (photo != null) {
            if (other.photo == null) return false
            if (!photo.contentEquals(other.photo)) return false
        } else if (other.photo != null) return false
        if (notes != other.notes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (phoneNumber?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (photo?.contentHashCode() ?: 0)
        result = 31 * result + (notes?.hashCode() ?: 0)
        return result
    }
}