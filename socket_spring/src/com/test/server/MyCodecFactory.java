package com.test.server;

import java.nio.charset.Charset;

import org.apache.mina.filter.codec.textline.TextLineCodecFactory;

/**
 * <style type="text/css">body{background:#C7EDCC;}</style>
 * ����༯����TextLineCodecFactory, ��ʾ��mina�����ݵĽ�����ʽ. ��������ֱ����TextLineCodecFactory��,
 * ��������Ŀ���Ǳ�ʾ���ǿ������Լ��ķ�ʽ������. MyCodecFactory.java
 * 
 * @author yan
 */
public class MyCodecFactory extends TextLineCodecFactory {
	public MyCodecFactory() {
		super(Charset.forName("UTF-8"));
	}
}
