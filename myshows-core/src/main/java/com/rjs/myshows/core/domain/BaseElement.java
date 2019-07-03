package com.rjs.myshows.core.domain;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.Identifiable;

public class BaseElement<ID extends Serializable> implements Identifiable<ID> {
	protected ID elementId;

	public BaseElement() {
	}

	public ID getElementId() {
		return elementId;
	}

	public void setElementId(ID elementId) {
		this.elementId = elementId;
	}

	@Override
	public ID getId() {
		return elementId;
	}

	public String getUniqueName() {
		return String.format("%s:%s", getClass().getName(), String.valueOf(elementId));
	}

	@Override
	public String toString() {
		return getUniqueName();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		BaseElement baseDto = (BaseElement) o;

		return Objects.equals(elementId, baseDto.getElementId());
	}

	@Override
	public int hashCode() {
		return elementId != null ? elementId.hashCode() : 0;
	}
}
