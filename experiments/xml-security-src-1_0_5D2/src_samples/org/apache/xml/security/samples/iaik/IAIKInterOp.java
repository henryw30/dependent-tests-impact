/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "<WebSig>" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation and was
 * originally based on software copyright (c) 2001, Institute for
 * Data Communications Systems, <http://www.nue.et-inf.uni-siegen.de/>.
 * The development of this software was partly funded by the European
 * Commission in the <WebSig> project in the ISIS Programme.
 * For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package org.apache.xml.security.samples.iaik;



import java.io.*;
import java.lang.reflect.*;
import java.security.PublicKey;
import java.security.cert.*;
import java.util.*;
import javax.xml.transform.TransformerException;
import org.apache.xpath.XPathAPI;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.apache.xml.security.c14n.*;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.*;
import org.apache.xml.security.keys.*;
import org.apache.xml.security.keys.content.*;
import org.apache.xml.security.keys.content.x509.*;
import org.apache.xml.security.keys.keyresolver.*;
import org.apache.xml.security.keys.storage.*;
import org.apache.xml.security.keys.storage.implementations.*;
import org.apache.xml.security.utils.*;
import org.apache.xml.security.Init;
import org.apache.xml.security.samples.utils.resolver.OfflineResolver;
import org.apache.xml.security.utils.resolver.*;
import org.apache.xml.security.utils.resolver.implementations.*;


/**
 *
 * @author $Author: geuerp $
 */
public class IAIKInterOp {

   /** {@link org.apache.log4j} logging facility */
   static org.apache.log4j.Category cat =
      org.apache.log4j.Category.getInstance(IAIKInterOp.class.getName());

   /** Field schemaValidate */
   static final boolean schemaValidate = false;

   /** Field signatureSchemaFile */
   static final String signatureSchemaFile = "data/xmldsig-core-schema.xsd";

   /**
    * Method main
    *
    * @param unused
    */
   public static void main(String unused[]) {

      if (schemaValidate) {
         System.out.println("We do schema-validation");
      } else {
         System.out.println("We do not schema-validation");
      }

      javax.xml.parsers.DocumentBuilderFactory dbf =
         javax.xml.parsers.DocumentBuilderFactory.newInstance();

      if (IAIKInterOp.schemaValidate) {
         dbf.setAttribute("http://apache.org/xml/features/validation/schema",
                          Boolean.TRUE);
         dbf.setAttribute(
            "http://apache.org/xml/features/dom/defer-node-expansion",
            Boolean.TRUE);
         dbf.setValidating(true);
         dbf.setAttribute("http://xml.org/sax/features/validation",
                          Boolean.TRUE);
         dbf.setAttribute(
            "http://apache.org/xml/properties/schema/external-schemaLocation",
            Constants.SignatureSpecNS + " " + IAIKInterOp.signatureSchemaFile);
      }

      dbf.setNamespaceAware(true);
      dbf.setAttribute("http://xml.org/sax/features/namespaces", Boolean.TRUE);

      //J-
      String gregorsDir =
         "data/at/iaik/ixsil/";
      String filenames[] = {
                              gregorsDir + "signatureAlgorithms/signatures/hMACSignature.xml"
                             ,gregorsDir + "signatureAlgorithms/signatures/hMACShortSignature.xml"
                             ,gregorsDir + "signatureAlgorithms/signatures/dSASignature.xml"
                             ,gregorsDir + "signatureAlgorithms/signatures/rSASignature.xml"
                             ,gregorsDir + "transforms/signatures/base64DecodeSignature.xml"
                             ,gregorsDir + "transforms/signatures/c14nSignature.xml"
                             ,gregorsDir + "coreFeatures/signatures/manifestSignature.xml"
                             ,gregorsDir + "transforms/signatures/xPathSignature.xml"
                             ,gregorsDir + "coreFeatures/signatures/signatureTypesSignature.xml"
                             ,gregorsDir + "transforms/signatures/envelopedSignatureSignature.xml"
                             };
      //J+
      verifyAnonymous(gregorsDir, dbf);

      for (int i = 0; i < 2; i++) {
         String signatureFileName = filenames[i];

         try {
            org.apache.xml.security.samples.signature
               .VerifyMerlinsExamplesFifteen.verifyHMAC(dbf, signatureFileName);
         } catch (Exception ex) {
            System.out.println("The XML signature in file "
                               + signatureFileName + " crashed the application (bad)");
            ex.printStackTrace();
            System.out.println();
         }
      }

      for (int i = 2; i < filenames.length; i++) {
         String signatureFileName = filenames[i];

         try {
            org.apache.xml.security.samples.signature
               .VerifyMerlinsExamplesSixteen.verify(dbf, signatureFileName);
         } catch (Exception ex) {
            System.out.println("The XML signature in file "
                               + signatureFileName + " crashed the application (bad)");
            ex.printStackTrace();
            System.out.println();
         }
      }
   }

   public static void verifyAnonymous(String gregorsDir, DocumentBuilderFactory dbf) {
         String filename =
            gregorsDir
            + "coreFeatures/signatures/anonymousReferenceSignature.xml";
      try {
         String anonymousRef =
            gregorsDir + "coreFeatures/samples/anonymousReferenceContent.xml";
         ResourceResolverSpi resolver = new ResolverAnonymous(anonymousRef);
         File f = new File(filename);

         System.out.println("Try to verify " + f.toURL().toString());

         javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
         org.w3c.dom.Document doc = db.parse(new java.io.FileInputStream(f));
         Element nscontext = XMLUtils.createDSctx(doc, "ds",
                                                  Constants.SignatureSpecNS);
         Element sigElement = (Element) XPathAPI.selectSingleNode(doc,
                                 "//ds:Signature[1]", nscontext);
         XMLSignature signature = new XMLSignature(sigElement,
                                                   f.toURL().toString());

         signature.setFollowNestedManifests(false);
         signature.addResourceResolver(resolver);

         KeyInfo ki = signature.getKeyInfo();

         if (ki != null) {
            X509Certificate cert = signature.getKeyInfo().getX509Certificate();

            if (cert != null) {
               System.out.println("The XML signature in file "
                                  + f.toURL().toString() + " is "
                                  + (signature.checkSignatureValue(cert)
                                     ? "valid (good)"
                                     : "invalid !!!!! (bad)"));
            } else {
               PublicKey pk = signature.getKeyInfo().getPublicKey();

               if (pk != null) {
                  System.out.println("The XML signature in file "
                                     + f.toURL().toString() + " is "
                                     + (signature.checkSignatureValue(pk)
                                        ? "valid (good)"
                                        : "invalid !!!!! (bad)"));
               } else {
                  System.out.println(
                     "Did not find a public key, so I can't check the signature");
               }
            }
         } else {
            System.out.println("Did not find a KeyInfo");
         }
      } catch (Exception ex) {
            System.out.println("The XML signature in file "
                               + filename + " crashed the application (bad)");
            ex.printStackTrace();
            System.out.println();
      }
   }

   static {
      org.apache.xml.security.Init.init();
   }
}
