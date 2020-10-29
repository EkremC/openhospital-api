/*
 * Open Hospital (www.open-hospital.org)
 * Copyright © 2006-2020 Informatici Senza Frontiere (info@informaticisenzafrontiere.org)
 *
 * Open Hospital is a free and open source software for healthcare data management.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * https://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.isf.dlvrtype.data;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.isf.dlvrtype.dto.DeliveryTypeDTO;
import org.isf.dlvrtype.model.DeliveryType;
import org.isf.dlvrtype.test.TestDeliveryType;
import org.isf.utils.exception.OHException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeliveryTypeHelper {

	public static DeliveryType setup(int i) throws OHException {
		TestDeliveryType testDeliveryType = new TestDeliveryType();
		DeliveryType deliveryType = testDeliveryType.setup(false);
		deliveryType.setCode(deliveryType.getCode() + i);
		return deliveryType;
	}

	public static ArrayList<DeliveryType> setupDeliveryTypeList(int size) {
		return (ArrayList<DeliveryType>) IntStream.range(0, size)
				.mapToObj(i -> {
					try {
						return DeliveryTypeHelper.setup(i);
					} catch (OHException e) {
						e.printStackTrace();
					}
					return null;
				}).collect(Collectors.toList());
	}

	public static String asJsonString(DeliveryTypeDTO body) {
		try {
			return new ObjectMapper().writeValueAsString(body);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
