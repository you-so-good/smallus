package com.smallus.payment.model.vo;

import com.smallus.classes.model.vo.Category;
import com.smallus.classes.model.vo.ClassDetail;
import com.smallus.classes.model.vo.Classes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassPayment {
	private ClassDetail classDetail = new ClassDetail();
	private Classes classes= new Classes();
	private Category category=new Category();

}
