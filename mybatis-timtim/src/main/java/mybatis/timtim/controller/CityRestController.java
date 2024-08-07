/*
 *    Copyright 2015-2024 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package mybatis.timtim.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mybatis.timtim.domain.City;
import mybatis.timtim.mapper.CityMapper;

import java.util.List;

@RequestMapping("/cities")
@RestController

public class CityRestController {

  private final CityMapper cityMapper;

  public CityRestController(CityMapper cityMapper) {
    this.cityMapper = cityMapper;
  }

  @GetMapping("{state}")
  City getCity(@PathVariable("state") String state) {
    return cityMapper.findByState(state);
  }
  @GetMapping("/all")
  List<City> getAllCity() {
    return cityMapper.findAllState();
  }
}
