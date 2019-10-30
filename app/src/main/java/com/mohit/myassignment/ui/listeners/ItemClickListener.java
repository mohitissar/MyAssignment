package com.mohit.myassignment.ui.listeners;

import com.mohit.myassignment.service.repository.storge.model.Facts;

/**
 * Created by Mohit Issar on 10/25/2019.
 */

public interface ItemClickListener {
    /**
     * This method get the Facts on click of facts details
     *
     * @param facts - fact
     */
    void OnItemClick(Facts facts);
}
