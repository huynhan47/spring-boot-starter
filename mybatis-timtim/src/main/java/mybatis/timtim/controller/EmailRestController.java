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


import mybatis.timtim.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/email")
@RestController

public class EmailRestController {
  @Value("${timtim.body}")
  private String body;
  private final EmailService emailSender ;

  public EmailRestController( EmailService emailSender) {
    this.emailSender = emailSender;

  }

  @GetMapping("{state}")
  void sendEmail(@PathVariable("state") String state) {




    emailSender.sendEmail("huynhan007@gmail.com","Test",this.body);
  }
//
//  @GetMapping("{state}")
//  emailSender.s("a","b","c");

}
