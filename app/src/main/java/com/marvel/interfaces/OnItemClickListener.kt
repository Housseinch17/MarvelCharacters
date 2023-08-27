package com.marvel.interfaces

import android.os.Bundle
import com.marvel.beans.Results

interface OnItemClickListener {
    fun navigateToCharacterDetailsFragment(DataArrayList: Results)
}