package com.E_Commerce.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponse<T> {
    private List<T> content; // The list of items for the current page
    private int page;        // Current page number
    private int size;        // Number of items per page
    private long totalItems; // Total number of items
    private int totalPages;  // Total number of pages
    private boolean last;    // Whether this is the last page
}
