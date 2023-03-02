package com.example.shoppeerw59.modal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseReq {
    @Min(value = 0, message = "Size phải lớn hơn 0")
    protected int pageSize;

    @Min(value = 0, message = "Số trang phải lớn hơn 0")
    protected int pageNumber;

    protected String sortBy;

    protected String sortType;

    public static PageRequest buildPageReq(BaseReq baseReq){
       PageRequest pageRequest = null;
       if (baseReq.getSortType().equals("DESC")) {
           pageRequest = PageRequest.of(baseReq.getPageNumber()-1, baseReq.getPageSize(), Sort.by(baseReq.getSortBy()).descending());
       }else {
            pageRequest = PageRequest.of(baseReq.getPageNumber()-1, baseReq.getPageSize(), Sort.by(baseReq.getSortBy()).ascending());

       }
        return pageRequest;
    }
}
