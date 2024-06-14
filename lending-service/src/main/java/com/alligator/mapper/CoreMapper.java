package com.alligator.mapper;

import java.util.List;

public interface CoreMapper<M,D> {
	D toDto(M model);

	M toEntity(D dto);

	List<D> toDto(List<M> model);

	List<M> toEntity(List<D> dto);
}