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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import com.google.zxing.qrcode.QRCodeWriter;
import mybatis.timtim.domain.City;
import mybatis.timtim.service.EmailHTMLService;
import mybatis.timtim.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;

@RequestMapping("/qr")
@RestController

public class QRRestController {
  //@Autowired
  private final CityMapper cityMapper;
  //@Autowired
  private final EmailHTMLService emailSender;


  public QRRestController(CityMapper cityMapper, EmailHTMLService emailSender) {
    this.cityMapper = cityMapper;
    this.emailSender = emailSender;

  }
  @GetMapping("{text}")
  public String generateQRCodeImage(@PathVariable("text") String barcodeText) throws Exception {
    QRCodeWriter barcodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix;
    BufferedImage bufferedImage ;
    File outputfile ;

    List<City> cities = cityMapper.findAllState();
    for (City city : cities) {
      bitMatrix
            = barcodeWriter.encode(city.getState(), BarcodeFormat.QR_CODE, 200, 200);
      bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
      outputfile = new File("D:\\"+city.getState()+".jpg");
      ImageIO.write(bufferedImage, "jpg", outputfile);

      emailSender.sendHtmlEmail("huynhan007@gmail.com", "Test","D:\\"+city.getState()+".jpg");
    }
    return "1";
  }
}
