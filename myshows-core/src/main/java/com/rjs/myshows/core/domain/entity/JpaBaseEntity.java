package com.rjs.myshows.core.domain.entity;

import javax.persistence.*;

import com.rjs.myshows.core.domain.BaseElement;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@MappedSuperclass
public abstract class JpaBaseEntity extends BaseElement<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@Override
	public Long getElementId() {
		return super.getElementId();
	}

	@Override
	public void setElementId(Long elementId) {
		super.setElementId(elementId);
	}
}
