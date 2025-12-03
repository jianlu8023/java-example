package com.github.jianlu8023.utils.web.utils;

import org.bouncycastle.cert.*;
import org.bouncycastle.cert.jcajce.*;
import org.bouncycastle.openssl.*;

import java.io.*;
import java.security.cert.*;

public class X509CertificateUtils {

    public static X509Certificate getX509Certificate(String certStr) throws CertificateException, IOException {
        Reader certificateReader = new BufferedReader(new StringReader(certStr));
        return readX509Certificate(certificateReader);
    }

    private static X509Certificate readX509Certificate(final Reader pemReader) throws IOException, CertificateException {
        try {
            Object pemObject = readPemObject(pemReader);
            X509CertificateHolder certificateHolder = asX509CertificateHolder(pemObject);
            return new JcaX509CertificateConverter().getCertificate(certificateHolder);
        } catch (PEMException e) {
            throw new CertificateException(e);
        }
    }

    private static Object readPemObject(final Reader reader) throws IOException {
        try (PEMParser parser = new PEMParser(reader)) {
            final Object result = parser.readObject(); // throws PEMException on parse error
            if (result == null) {
                throw new PEMException("Invalid PEM content");
            }
            return result;
        }
    }

    private static X509CertificateHolder asX509CertificateHolder(final Object pemObject) throws CertificateException {
        if (pemObject instanceof X509CertificateHolder) {
            return (X509CertificateHolder) pemObject;
        } else {
            throw new CertificateException("Unexpected PEM content type: " + pemObject.getClass().getSimpleName());
        }
    }
}
