package com.agen.ttw.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.namespace.QName;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.PositionInParagraph;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.TextSegement;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRelation;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.contenttype.ContentType;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.WordprocessingML.AlternativeFormatInputPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.org.apache.poi.util.IOUtils;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.CTAltChunk;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectFrameLocking;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGraphicFrameProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTEffectExtent;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosV;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapNone;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapThrough;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapTight;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapTopBottom;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STRelFromH;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STRelFromV;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHighlight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPicture;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridCol;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextScale;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STEm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHeightRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnderline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalAlignRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;
import org.springframework.stereotype.Component;

/**
 * 
 * <label>
 *		word操作工作.
 * </label>
 * <p>
 *		<pre>
 *			
 *		</pre>
 * </p>
 * @ClassName WordUtil
 * @author tgj  
 * @date 2017年10月30日 下午1:24:41 
 *    
 * @Copyright 2017 www.agen.com Inc. All rights reserved.
 */
@Component("ttwWordUtil")
public class WordUtil {
	
	private static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	
	private InputStream is;
	
	private XWPFDocument docx;

	/**
	 * 添加标签
	 * @param p
	 * @param content
	 * @param markId
	 * @param bookMarkName
	 * @param isInsert
	 * @param isNewLine
	 * @param fontFamily
	 * @param fontSize
	 * @param colorVal
	 * @param isBlod
	 * @param isUnderLine
	 * @param underLineColor
	 * @param underStyle
	 * @param isItalic
	 * @param isStrike
	 */
	public void addParagraphContentBookmarkBasicStyle(XWPFParagraph p,
			String content, BigInteger markId, String bookMarkName,
			boolean isInsert, boolean isNewLine, String fontFamily,
			String fontSize, String colorVal, boolean isBlod,
			boolean isUnderLine, String underLineColor,
			STUnderline.Enum underStyle, boolean isItalic, boolean isStrike) {
		CTBookmark bookStart = p.getCTP().addNewBookmarkStart();
		bookStart.setId(markId);
		bookStart.setName(bookMarkName);

		XWPFRun pRun = getOrAddParagraphFirstRun(p, isInsert, isNewLine);
		setParagraphRunFontInfo(p, pRun, content, fontFamily, fontSize);
		setParagraphTextStyleInfo(p, pRun, colorVal, isBlod, isUnderLine,
				underLineColor, underStyle, isItalic, isStrike, false, false,
				false, false, false, false, false, null, false, null, false,
				null, null, null, 0, 0, 0);
		CTMarkupRange bookEnd = p.getCTP().addNewBookmarkEnd();
		bookEnd.setId(markId);

	}
	
	/**
	 * 添加书签
	 * @param p
	 * @param content
	 * @param markId
	 * @param bookMarkName
	 * @param isInsert
	 * @param isNewLine
	 * @param fontFamily
	 * @param fontSize
	 * @param colorVal
	 * @param isBlod
	 * @param isUnderLine
	 * @param underLineColor
	 * @param underStyle
	 * @param isItalic
	 * @param isStrike
	 * @param isDStrike
	 * @param isShadow
	 * @param isVanish
	 * @param isEmboss
	 * @param isImprint
	 * @param isOutline
	 * @param isEm
	 * @param emType
	 * @param isHightLight
	 * @param hightStyle
	 * @param isShd
	 * @param shdStyle
	 * @param shdColor
	 * @param verticalAlign
	 * @param position
	 * @param spacingValue
	 * @param indent
	 */
	public void addParagraphContentBookmark(XWPFParagraph p, String content,
			BigInteger markId, String bookMarkName, boolean isInsert,
			boolean isNewLine, String fontFamily, String fontSize,
			String colorVal, boolean isBlod, boolean isUnderLine,
			String underLineColor, STUnderline.Enum underStyle,
			boolean isItalic, boolean isStrike, boolean isDStrike,
			boolean isShadow, boolean isVanish, boolean isEmboss,
			boolean isImprint, boolean isOutline, boolean isEm,
			STEm.Enum emType, boolean isHightLight,
			STHighlightColor.Enum hightStyle, boolean isShd,
			STShd.Enum shdStyle, String shdColor, VerticalAlign verticalAlign,
			int position, int spacingValue, int indent) {
		CTBookmark bookStart = p.getCTP().addNewBookmarkStart();
		bookStart.setId(markId);
		bookStart.setName(bookMarkName);

		XWPFRun pRun = getOrAddParagraphFirstRun(p, isInsert, isNewLine);
		setParagraphRunFontInfo(p, pRun, content, fontFamily, fontSize);
		setParagraphTextStyleInfo(p, pRun, colorVal, isBlod, isUnderLine,
				underLineColor, underStyle, isItalic, isStrike, isDStrike,
				isShadow, isVanish, isEmboss, isImprint, isOutline, isEm,
				emType, isHightLight, hightStyle, isShd, shdStyle, shdColor,
				verticalAlign, position, spacingValue, indent);

		CTMarkupRange bookEnd = p.getCTP().addNewBookmarkEnd();
		bookEnd.setId(markId);

	}

	/**
	 * 默认的超链接样式
	 * @param paragraph
	 * @param url
	 * @param text
	 * @param fontFamily
	 * @param fontSize
	 * @param colorVal
	 * @param isBlod
	 * @param isItalic
	 * @param isStrike
	 */
	public void addParagraphTextHyperlinkBasicStyle(XWPFParagraph paragraph,
			String url, String text, String fontFamily, String fontSize,
			String colorVal, boolean isBlod, boolean isItalic, boolean isStrike) {
		addParagraphTextHyperlink(paragraph, url, text, fontFamily, fontSize,
				colorVal, isBlod, true, "0000FF", STUnderline.SINGLE, isItalic,
				isStrike, false, false, false, false, false, false, false,
				null, false, null, false, null, null, null, 0, 0, 0);
	}

	/**
	 * 设置超链接样式
	 * @param paragraph
	 * @param url
	 * @param text
	 * @param fontFamily
	 * @param fontSize
	 * @param colorVal
	 * @param isBlod
	 * @param isUnderLine
	 * @param underLineColor
	 * @param underStyle
	 * @param isItalic
	 * @param isStrike
	 * @param isDStrike
	 * @param isShadow
	 * @param isVanish
	 * @param isEmboss
	 * @param isImprint
	 * @param isOutline
	 * @param isEm
	 * @param emType
	 * @param isHightLight
	 * @param hightStyle
	 * @param isShd
	 * @param shdStyle
	 * @param shdColor
	 * @param verticalAlign
	 * @param position
	 * @param spacingValue
	 * @param indent
	 */
	public void addParagraphTextHyperlink(XWPFParagraph paragraph, String url,
			String text, String fontFamily, String fontSize, String colorVal,
			boolean isBlod, boolean isUnderLine, String underLineColor,
			STUnderline.Enum underStyle, boolean isItalic, boolean isStrike,
			boolean isDStrike, boolean isShadow, boolean isVanish,
			boolean isEmboss, boolean isImprint, boolean isOutline,
			boolean isEm, STEm.Enum emType, boolean isHightLight,
			STHighlightColor.Enum hightStyle, boolean isShd,
			STShd.Enum shdStyle, String shdColor,
			STVerticalAlignRun.Enum verticalAlign, int position,
			int spacingValue, int indent) {
		// Add the link as External relationship
		String id = paragraph
				.getDocument()
				.getPackagePart()
				.addExternalRelationship(url,
						XWPFRelation.HYPERLINK.getRelation()).getId();
		// Append the link and bind it to the relationship
		CTHyperlink cLink = paragraph.getCTP().addNewHyperlink();
		cLink.setId(id);

		// Create the linked text
		CTText ctText = CTText.Factory.newInstance();
		ctText.setStringValue(text);
		CTR ctr = CTR.Factory.newInstance();
		CTRPr rpr = ctr.addNewRPr();

		if (StringUtils.isNotBlank(fontFamily)) {
			// 设置字体
			CTFonts fonts = rpr.isSetRFonts() ? rpr.getRFonts() : rpr
					.addNewRFonts();
			fonts.setAscii(fontFamily);
			fonts.setEastAsia(fontFamily);
			fonts.setHAnsi(fontFamily);
		}
		if (StringUtils.isNotBlank(fontSize)) {
			// 设置字体大小
			CTHpsMeasure sz = rpr.isSetSz() ? rpr.getSz() : rpr.addNewSz();
			sz.setVal(new BigInteger(fontSize));

			CTHpsMeasure szCs = rpr.isSetSzCs() ? rpr.getSzCs() : rpr
					.addNewSzCs();
			szCs.setVal(new BigInteger(fontSize));
		}
		// 设置超链接样式
		// 字体颜色
		if (StringUtils.isNotBlank(colorVal)) {
			CTColor color = CTColor.Factory.newInstance();
			color.setVal(colorVal);
			rpr.setColor(color);
		}
		// 加粗
		if (isBlod) {
			CTOnOff bCtOnOff = rpr.addNewB();
			bCtOnOff.setVal(STOnOff.TRUE);
		}
		// 下划线
		if (isUnderLine) {
			CTUnderline udLine = rpr.addNewU();
			udLine.setVal(underStyle);
			udLine.setColor(underLineColor);
		}
		// 倾斜
		if (isItalic) {
			CTOnOff iCtOnOff = rpr.addNewI();
			iCtOnOff.setVal(STOnOff.TRUE);
		}
		// 删除线
		if (isStrike) {
			CTOnOff sCtOnOff = rpr.addNewStrike();
			sCtOnOff.setVal(STOnOff.TRUE);
		}
		// 双删除线
		if (isDStrike) {
			CTOnOff dsCtOnOff = rpr.addNewDstrike();
			dsCtOnOff.setVal(STOnOff.TRUE);
		}
		// 阴影
		if (isShadow) {
			CTOnOff shadowCtOnOff = rpr.addNewShadow();
			shadowCtOnOff.setVal(STOnOff.TRUE);
		}
		// 隐藏
		if (isVanish) {
			CTOnOff vanishCtOnOff = rpr.addNewVanish();
			vanishCtOnOff.setVal(STOnOff.TRUE);
		}
		// 阳文
		if (isEmboss) {
			CTOnOff embossCtOnOff = rpr.addNewEmboss();
			embossCtOnOff.setVal(STOnOff.TRUE);
		}
		// 阴文
		if (isImprint) {
			CTOnOff isImprintCtOnOff = rpr.addNewImprint();
			isImprintCtOnOff.setVal(STOnOff.TRUE);
		}
		// 空心
		if (isOutline) {
			CTOnOff isOutlineCtOnOff = rpr.addNewOutline();
			isOutlineCtOnOff.setVal(STOnOff.TRUE);
		}
		// 着重号
		if (isEm) {
			CTEm em = rpr.addNewEm();
			em.setVal(emType);
		}
		// 突出显示文本
		if (isHightLight) {
			if (hightStyle != null) {
				CTHighlight hightLight = rpr.addNewHighlight();
				hightLight.setVal(hightStyle);
			}
		}
		if (isShd) {
			// 设置底纹
			CTShd shd = rpr.addNewShd();
			if (shdStyle != null) {
				shd.setVal(shdStyle);
			}
			if (shdColor != null) {
				shd.setColor(shdColor);
			}
		}
		// 上标下标
		if (verticalAlign != null) {
			rpr.addNewVertAlign().setVal(verticalAlign);
		}
		// 设置文本位置
		rpr.addNewPosition().setVal(new BigInteger(String.valueOf(position)));
		if (spacingValue != 0) {
			// 设置字符间距信息
			CTSignedTwipsMeasure ctSTwipsMeasure = rpr.addNewSpacing();
			ctSTwipsMeasure
					.setVal(new BigInteger(String.valueOf(spacingValue)));
		}
		// 设置字符间距缩进
		if (indent > 0) {
			CTTextScale paramCTTextScale = rpr.addNewW();
			paramCTTextScale.setVal(indent);
		}
		ctr.setTArray(new CTText[] { ctText });
		cLink.setRArray(new CTR[] { ctr });
	}

	/**
	 * 页脚:显示页码信息
	 * @param document
	 * @throws Exception
	 */
	public void simpleNumberFooter(XWPFDocument document) throws Exception {
		CTP ctp = CTP.Factory.newInstance();
		XWPFParagraph codePara = new XWPFParagraph(ctp, document);
		XWPFRun r1 = codePara.createRun();
		r1.setText("第");
		setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

		r1 = codePara.createRun();
		CTFldChar fldChar = r1.getCTR().addNewFldChar();
		fldChar.setFldCharType(STFldCharType.BEGIN);

		r1 = codePara.createRun();
		CTText ctText = r1.getCTR().addNewInstrText();
		ctText.setStringValue("PAGE  \\* MERGEFORMAT");
		ctText.setSpace(SpaceAttribute.Space.PRESERVE);
		setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

		fldChar = r1.getCTR().addNewFldChar();
		fldChar.setFldCharType(STFldCharType.END);

		r1 = codePara.createRun();
		r1.setText("页 总共");
		setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

		r1 = codePara.createRun();
		fldChar = r1.getCTR().addNewFldChar();
		fldChar.setFldCharType(STFldCharType.BEGIN);

		r1 = codePara.createRun();
		ctText = r1.getCTR().addNewInstrText();
		ctText.setStringValue("NUMPAGES  \\* MERGEFORMAT ");
		ctText.setSpace(SpaceAttribute.Space.PRESERVE);
		setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

		fldChar = r1.getCTR().addNewFldChar();
		fldChar.setFldCharType(STFldCharType.END);

		r1 = codePara.createRun();
		r1.setText("页");
		setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

		setParagraphAlignInfo(codePara, ParagraphAlignment.CENTER,
				TextAlignment.CENTER);
		codePara.setBorderTop(Borders.THICK);
		XWPFParagraph[] newparagraphs = new XWPFParagraph[1];
		newparagraphs[0] = codePara;
		CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
		XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(
				document, sectPr);
		headerFooterPolicy.createFooter(STHdrFtr.DEFAULT, newparagraphs);
	}

	/**
	 * 页眉:显示时间信息
	 * @param document
	 * @throws Exception
	 */
	public void simpleDateHeader(XWPFDocument document) throws Exception {
		CTP ctp = CTP.Factory.newInstance();
		XWPFParagraph codePara = new XWPFParagraph(ctp, document);

		XWPFRun r1 = codePara.createRun();
		CTFldChar fldChar = r1.getCTR().addNewFldChar();
		fldChar.setFldCharType(STFldCharType.BEGIN);

		r1 = codePara.createRun();
		CTText ctText = r1.getCTR().addNewInstrText();
		ctText.setStringValue("TIME \\@ \"EEEE\"");
		ctText.setSpace(SpaceAttribute.Space.PRESERVE);
		setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

		fldChar = r1.getCTR().addNewFldChar();
		fldChar.setFldCharType(STFldCharType.END);

		r1 = codePara.createRun();
		r1.setText("年");
		setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

		r1 = codePara.createRun();
		fldChar = r1.getCTR().addNewFldChar();
		fldChar.setFldCharType(STFldCharType.BEGIN);

		r1 = codePara.createRun();
		ctText = r1.getCTR().addNewInstrText();
		ctText.setStringValue("TIME \\@ \"O\"");
		ctText.setSpace(SpaceAttribute.Space.PRESERVE);
		setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

		fldChar = r1.getCTR().addNewFldChar();
		fldChar.setFldCharType(STFldCharType.END);

		r1 = codePara.createRun();
		r1.setText("月");
		r1.setFontSize(11);
		setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

		r1 = codePara.createRun();
		fldChar = r1.getCTR().addNewFldChar();
		fldChar.setFldCharType(STFldCharType.BEGIN);

		r1 = codePara.createRun();
		ctText = r1.getCTR().addNewInstrText();
		ctText.setStringValue("TIME \\@ \"A\"");
		ctText.setSpace(SpaceAttribute.Space.PRESERVE);
		setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

		fldChar = r1.getCTR().addNewFldChar();
		fldChar.setFldCharType(STFldCharType.END);

		r1 = codePara.createRun();
		r1.setText("日");
		r1.setFontSize(11);
		setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

		setParagraphAlignInfo(codePara, ParagraphAlignment.CENTER,
				TextAlignment.CENTER);
		codePara.setBorderBottom(Borders.THICK);
		XWPFParagraph[] newparagraphs = new XWPFParagraph[1];
		newparagraphs[0] = codePara;
		CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
		XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(
				document, sectPr);
		headerFooterPolicy.createHeader(STHdrFtr.DEFAULT, newparagraphs);
	}
	
	/**
	 * 创建段落
	 * @param docx
	 * @return
	 */
	public XWPFParagraph createParagraph(XWPFDocument docx) {
		return docx.createParagraph();
	}

	/**
	 * 得到段落CTPPr
	 * @param p
	 * @return
	 */
	public CTPPr getParagraphCTPPr(XWPFParagraph p) {
		CTPPr pPPr = null;
		if (p.getCTP() != null) {
			if (p.getCTP().getPPr() != null) {
				pPPr = p.getCTP().getPPr();
			} else {
				pPPr = p.getCTP().addNewPPr();
			}
		}
		return pPPr;
	}
	
	/**
	 * 删除段落
	 * @param docx
	 * @param p
	 * @return
	 */
	public boolean deleteParagraph(XWPFDocument docx, XWPFParagraph p) {
		Iterator<IBodyElement> it = docx.getBodyElementsIterator();
		int index = -1;
		while (it.hasNext()) {
			index++;
			IBodyElement ie = it.next();
			if(ie.getElementType() == BodyElementType.PARAGRAPH && ie.equals(p)) {
				return docx.removeBodyElement(index);
			}
			
		}
		return false;
	}
	
	
	/**
	 * 得到table、paragraph位置
	 * @param docx
	 * @param obj
	 * @return
	 */
	public int getElementIndex(XWPFDocument docx, Object obj) {
		Iterator<IBodyElement> it = docx.getBodyElementsIterator();
		int index = -1;
		while (it.hasNext()) {
			index++;
			IBodyElement ie = it.next();
			if(ie.equals(obj)) {
				return index;
			}
		}
		return -1;
	}
	
	/**
	 * 得到runIndex
	 * @param run
	 * @return
	 */
	public int getRunIndex(XWPFRun run) {
		XWPFParagraph paragraph = getParagraph(run);
		if(null == paragraph) return -1;
		List<XWPFRun> runs = paragraph.getRuns();
		int size = runs.size();
		for (int i = 0; i < size; i++) {
			if(runs.get(i).equals(run)) return i;
		}
		return -1;
	}
	
	/**
	 * 删除指定位置元素
	 * @param docx
	 * @param pos
	 * @return
	 */
	public boolean deleteParagraph(XWPFDocument docx, int pos) {
		if(docx.getBodyElements().size() > pos) return docx.removeBodyElement(pos);
		return false;
	}
	
	/**
	 * 插入段落
	 * @param docx
	 * @param pos
	 * @return
	 */
	public XWPFParagraph insertParagraph(XWPFDocument docx, int pos) {
		CTBody ctBody = docx.getDocument().getBody();
		List<CTP> pList = ctBody.getPList();
//		CTP ctp = newp.getCTP();
//		XWPFParagraph newP = docx.getParagraph(ctp);
		if(pList.size() > pos) {
			XmlCursor cursor = ctBody.getPArray(pos).newCursor();
			return docx.insertNewParagraph(cursor);
		}
		return null;
	}

	/**
	 * 得到XWPFRun的CTRPr
	 * @param p
	 * @param pRun
	 * @return
	 */
	public CTRPr getRunCTRPr(XWPFParagraph p, XWPFRun pRun) {
		CTRPr pRpr = null;
		if (pRun.getCTR() != null) {
			pRpr = pRun.getCTR().getRPr();
			if (pRpr == null) {
				pRpr = pRun.getCTR().addNewRPr();
			}
		} else {
			pRpr = p.getCTP().addNewR().addNewRPr();
		}
		return pRpr;
	}

	/**
	 * 得到或创建第一个XWPFRun
	 * @param p
	 * @param isInsert
	 * @param isNewLine
	 * @return
	 */
	public XWPFRun getOrAddParagraphFirstRun(XWPFParagraph p, boolean isInsert,
			boolean isNewLine) {
		XWPFRun pRun = null;
		if (isInsert) {
			pRun = p.createRun();
		} else {
			if (p.getRuns() != null && p.getRuns().size() > 0) {
				pRun = p.getRuns().get(0);
			} else {
				pRun = p.createRun();
			}
		}
		if (isNewLine) {
			pRun.addBreak();
		}
		return pRun;
	}

	/**
	 * 设置段落相关信息
	 * @param p
	 * @param isInsert
	 * @param isNewLine
	 * @param content
	 * @param fontFamily
	 * @param fontSize
	 */
	public void setParagraphTextFontInfo(XWPFParagraph p, boolean isInsert,
			boolean isNewLine, String content, String fontFamily,
			String fontSize) {
		XWPFRun pRun = getOrAddParagraphFirstRun(p, isInsert, isNewLine);
		setParagraphRunFontInfo(p, pRun, content, fontFamily, fontSize);
	}
	
	/**
	 * 设置文本字体大小等
	 * @param run
	 * @param content
	 * @param fontFamily
	 * @param fontSize
	 */
	public void setParagraphTextFontInfo(XWPFRun run, String content, String fontFamily,
			String fontSize) {
		XWPFParagraph p = getParagraph(run);
		if (null != p)
			setParagraphRunFontInfo(p, run, content, fontFamily, fontSize);
		else
			throw new UnknownError("未知类型！");
	}

	/**
	 * 得到paragraph
	 * @param run
	 * @return
	 */
	public XWPFParagraph getParagraph(XWPFRun run) {
		IRunBody iRunBody = run.getParent();
		if (iRunBody instanceof XWPFParagraph) return (XWPFParagraph) iRunBody;
		return null;
	}
	
	/**
	 * 设置字体信息
	 * @param p
	 * @param pRun
	 * @param content
	 * @param fontFamily
	 * @param fontSize
	 */
	public void setParagraphRunFontInfo(XWPFParagraph p, XWPFRun pRun,
			String content, String fontFamily, String fontSize) {
		CTRPr pRpr = getRunCTRPr(p, pRun);
		if (StringUtils.isNotBlank(content)) {
			pRun.setText(content);
		}
		if(null != fontFamily) {
			// 设置字体
			CTFonts fonts = pRpr.isSetRFonts() ? pRpr.getRFonts() : pRpr
					.addNewRFonts();
			fonts.setAscii(fontFamily);
			fonts.setEastAsia(fontFamily);
			fonts.setHAnsi(fontFamily);
		}

		if(null != fontSize) {
			// 设置字体大小
			CTHpsMeasure sz = pRpr.isSetSz() ? pRpr.getSz() : pRpr.addNewSz();
			sz.setVal(new BigInteger(fontSize));
	
			CTHpsMeasure szCs = pRpr.isSetSzCs() ? pRpr.getSzCs() : pRpr
					.addNewSzCs();
			szCs.setVal(new BigInteger(fontSize));
		}
	}
	
	/**
	 * 设置段落基本样式
	 * @param p
	 * @param pRun
	 * @param colorVal
	 * @param isBlod
	 * @param isUnderLine
	 * @param underLineColor
	 * @param underStyle
	 * @param isItalic
	 * @param isStrike
	 * @param isHightLight
	 * @param hightStyle
	 * @param isShd
	 * @param shdStyle
	 * @param shdColor
	 */
	public void setParagraphTextBasicStyleInfo(XWPFParagraph p, XWPFRun pRun,
			String colorVal, boolean isBlod, boolean isUnderLine,
			String underLineColor, STUnderline.Enum underStyle,
			boolean isItalic, boolean isStrike, boolean isHightLight,
			STHighlightColor.Enum hightStyle, boolean isShd,
			STShd.Enum shdStyle, String shdColor) {
		setParagraphTextStyleInfo(p, pRun, colorVal, isBlod, isUnderLine,
				underLineColor, underStyle, isItalic, isStrike, false, false,
				false, false, false, false, false, null, isHightLight,
				hightStyle, isShd, shdStyle, shdColor, null, 0, 0, 0);
	}

	/**
	 * 设置段落文本样式(高亮与底纹显示效果不同)设置字符间距信息(CTSignedTwipsMeasure)
	 * @param p
	 * @param pRun
	 * @param colorVal
	 * @param isBlod
	 * @param isUnderLine
	 * @param underLineColor
	 * @param underStyle
	 * @param isItalic
	 * @param isStrike
	 * @param isHightLight
	 * @param hightStyle
	 * @param isShd
	 * @param shdStyle
	 * @param shdColor
	 * @param verticalAlign
	 * @param position
	 * @param spacingValue
	 * @param indent
	 */
	public void setParagraphTextSimpleStyleInfo(XWPFParagraph p, XWPFRun pRun,
			String colorVal, boolean isBlod, boolean isUnderLine,
			String underLineColor, STUnderline.Enum underStyle,
			boolean isItalic, boolean isStrike, boolean isHightLight,
			STHighlightColor.Enum hightStyle, boolean isShd,
			STShd.Enum shdStyle, String shdColor, VerticalAlign verticalAlign,
			int position, int spacingValue, int indent) {
		setParagraphTextStyleInfo(p, pRun, colorVal, isBlod, isUnderLine,
				underLineColor, underStyle, isItalic, isStrike, false, false,
				false, false, false, false, false, null, isHightLight,
				hightStyle, isShd, shdStyle, shdColor, verticalAlign, position,
				spacingValue, indent);
	}

	/**
	 * 设置段落文本样式(高亮与底纹显示效果不同)设置字符间距信息(CTSignedTwipsMeasure)
	 * @param p
	 * @param pRun
	 * @param colorVal
	 * @param isBlod
	 * @param isUnderLine
	 * @param underLineColor
	 * @param underStyle
	 * @param isItalic
	 * @param isStrike
	 * @param isDStrike
	 * @param isShadow
	 * @param isVanish
	 * @param isEmboss
	 * @param isImprint
	 * @param isOutline
	 * @param isEm
	 * @param emType
	 * @param isHightLight
	 * @param hightStyle
	 * @param isShd
	 * @param shdStyle
	 * @param shdColor
	 * @param verticalAlign
	 * @param position
	 * @param spacingValue
	 * @param indent
	 */
	public void setParagraphTextStyleInfo(XWPFParagraph p, XWPFRun pRun,
			String colorVal, boolean isBlod, boolean isUnderLine,
			String underLineColor, STUnderline.Enum underStyle,
			boolean isItalic, boolean isStrike, boolean isDStrike,
			boolean isShadow, boolean isVanish, boolean isEmboss,
			boolean isImprint, boolean isOutline, boolean isEm,
			STEm.Enum emType, boolean isHightLight,
			STHighlightColor.Enum hightStyle, boolean isShd,
			STShd.Enum shdStyle, String shdColor, VerticalAlign verticalAlign,
			int position, int spacingValue, int indent) {
		if (pRun == null) {
			return;
		}
		CTRPr pRpr = getRunCTRPr(p, pRun);
		if (colorVal != null) {
			pRun.setColor(colorVal);
		}
		// 设置字体样式
		// 加粗
		if (isBlod) {
			pRun.setBold(isBlod);
		}
		// 倾斜
		if (isItalic) {
			pRun.setItalic(isItalic);
		}
		// 删除线
		if (isStrike) {
			pRun.setStrikeThrough(true);
		}
		// 双删除线
		if (isDStrike) {
			CTOnOff dsCtOnOff = pRpr.isSetDstrike() ? pRpr.getDstrike() : pRpr
					.addNewDstrike();
			dsCtOnOff.setVal(STOnOff.TRUE);
		}
		// 阴影
		if (isShadow) {
			CTOnOff shadowCtOnOff = pRpr.isSetShadow() ? pRpr.getShadow()
					: pRpr.addNewShadow();
			shadowCtOnOff.setVal(STOnOff.TRUE);
		}
		// 隐藏
		if (isVanish) {
			CTOnOff vanishCtOnOff = pRpr.isSetVanish() ? pRpr.getVanish()
					: pRpr.addNewVanish();
			vanishCtOnOff.setVal(STOnOff.TRUE);
		}
		// 阳文
		if (isEmboss) {
			CTOnOff embossCtOnOff = pRpr.isSetEmboss() ? pRpr.getEmboss()
					: pRpr.addNewEmboss();
			embossCtOnOff.setVal(STOnOff.TRUE);
		}
		// 阴文
		if (isImprint) {
			CTOnOff isImprintCtOnOff = pRpr.isSetImprint() ? pRpr.getImprint()
					: pRpr.addNewImprint();
			isImprintCtOnOff.setVal(STOnOff.TRUE);
		}
		// 空心
		if (isOutline) {
			CTOnOff isOutlineCtOnOff = pRpr.isSetOutline() ? pRpr.getOutline()
					: pRpr.addNewOutline();
			isOutlineCtOnOff.setVal(STOnOff.TRUE);
		}
		// 着重号
		if (isEm) {
			CTEm em = pRpr.isSetEm() ? pRpr.getEm() : pRpr.addNewEm();
			em.setVal(emType);
		}
		// 设置下划线样式
		if (isUnderLine) {
			CTUnderline u = pRpr.isSetU() ? pRpr.getU() : pRpr.addNewU();
			if (underStyle != null) {
				u.setVal(underStyle);
			}
			if (underLineColor != null) {
				u.setColor(underLineColor);
			}
		}
		// 设置突出显示文本
		if (isHightLight) {
			if (hightStyle != null) {
				CTHighlight hightLight = pRpr.isSetHighlight() ? pRpr
						.getHighlight() : pRpr.addNewHighlight();
				hightLight.setVal(hightStyle);
			}
		}
		if (isShd) {
			// 设置底纹
			CTShd shd = pRpr.isSetShd() ? pRpr.getShd() : pRpr.addNewShd();
			if (shdStyle != null) {
				shd.setVal(shdStyle);
			}
			if (shdColor != null) {
				shd.setColor(shdColor);
			}
		}
		// 上标下标
		if (verticalAlign != null) {
			pRun.setSubscript(verticalAlign);
		}
		// 设置文本位置
		pRun.setTextPosition(position);
		if (spacingValue > 0) {
			// 设置字符间距信息
			CTSignedTwipsMeasure ctSTwipsMeasure = pRpr.isSetSpacing() ? pRpr
					.getSpacing() : pRpr.addNewSpacing();
			ctSTwipsMeasure
					.setVal(new BigInteger(String.valueOf(spacingValue)));
		}
		if (indent > 0) {
			CTTextScale paramCTTextScale = pRpr.isSetW() ? pRpr.getW() : pRpr
					.addNewW();
			paramCTTextScale.setVal(indent);
		}
	}

	/**
	 * 设置段落底纹(对整段文字起作用)
	 * @param p
	 * @param isShd
	 * @param shdStyle
	 * @param shdColor
	 */
	public void setParagraphShdStyle(XWPFParagraph p, boolean isShd,
			STShd.Enum shdStyle, String shdColor) {
		CTPPr pPpr = getParagraphCTPPr(p);
		CTShd shd = pPpr.isSetShd() ? pPpr.getShd() : pPpr.addNewShd();
		if (shdStyle != null) {
			shd.setVal(shdStyle);
		}
		if (shdColor != null) {
			shd.setColor(shdColor);
		}
	}

	/**
	 * 设置段落间距信息,一行=100 一磅=20
	 * @param p
	 * @param isSpace
	 * @param before
	 * @param after
	 * @param beforeLines
	 * @param afterLines
	 * @param isLine
	 * @param line
	 * @param lineValue
	 */
	public void setParagraphSpacingInfo(XWPFParagraph p, boolean isSpace,
			String before, String after, String beforeLines, String afterLines,
			boolean isLine, String line, STLineSpacingRule.Enum lineValue) {
		CTPPr pPPr = getParagraphCTPPr(p);
		CTSpacing pSpacing = pPPr.getSpacing() != null ? pPPr.getSpacing()
				: pPPr.addNewSpacing();
		if (isSpace) {
			// 段前磅数
			if (before != null) {
				pSpacing.setBefore(new BigInteger(before));
			}
			// 段后磅数
			if (after != null) {
				pSpacing.setAfter(new BigInteger(after));
			}
			// 段前行数
			if (beforeLines != null) {
				pSpacing.setBeforeLines(new BigInteger(beforeLines));
			}
			// 段后行数
			if (afterLines != null) {
				pSpacing.setAfterLines(new BigInteger(afterLines));
			}
		}
		// 间距
		if (isLine) {
			if (line != null) {
				pSpacing.setLine(new BigInteger(line));
			}
			if (lineValue != null) {
				pSpacing.setLineRule(lineValue);
			}
		}
	}

	/**
	 * 设置段落缩进信息 1厘米≈567
	 * @param p
	 * @param firstLine
	 * @param firstLineChar
	 * @param hanging
	 * @param hangingChar
	 * @param right
	 * @param rigthChar
	 * @param left
	 * @param leftChar
	 */
	public void setParagraphIndInfo(XWPFParagraph p, String firstLine,
			String firstLineChar, String hanging, String hangingChar,
			String right, String rigthChar, String left, String leftChar) {
		CTPPr pPPr = getParagraphCTPPr(p);
		CTInd pInd = pPPr.getInd() != null ? pPPr.getInd() : pPPr.addNewInd();
		if (firstLine != null) {
			pInd.setFirstLine(new BigInteger(firstLine));
		}
		if (firstLineChar != null) {
			pInd.setFirstLineChars(new BigInteger(firstLineChar));
		}
		if (hanging != null) {
			pInd.setHanging(new BigInteger(hanging));
		}
		if (hangingChar != null) {
			pInd.setHangingChars(new BigInteger(hangingChar));
		}
		if (left != null) {
			pInd.setLeft(new BigInteger(left));
		}
		if (leftChar != null) {
			pInd.setLeftChars(new BigInteger(leftChar));
		}
		if (right != null) {
			pInd.setRight(new BigInteger(right));
		}
		if (rigthChar != null) {
			pInd.setRightChars(new BigInteger(rigthChar));
		}
	}

	/**
	 * 段落边框
	 * @param p
	 * @param lborder
	 * @param tBorders
	 * @param rBorders
	 * @param bBorders
	 * @param btborders
	 */
	public void setParagraphBorders(XWPFParagraph p, Borders lborder,
			Borders tBorders, Borders rBorders, Borders bBorders,
			Borders btborders) {
		if (lborder != null) {
			p.setBorderLeft(lborder);
		}
		if (tBorders != null) {
			p.setBorderTop(tBorders);
		}
		if (rBorders != null) {
			p.setBorderRight(rBorders);
		}
		if (bBorders != null) {
			p.setBorderBottom(bBorders);
		}
		if (btborders != null) {
			p.setBorderBetween(btborders);
		}
	}

	/**
	 * 设置段落对齐
	 * @param p
	 * @param pAlign
	 * @param valign
	 */
	public void setParagraphAlignInfo(XWPFParagraph p,
			ParagraphAlignment pAlign, TextAlignment valign) {
		if (pAlign != null) {
			p.setAlignment(pAlign);
		}
		if (valign != null) {
			p.setVerticalAlignment(valign);
		}
	}

	/**
	 * 删除指定位置的表格
	 * @param xdoc
	 * @param pos
	 */
	public void deleteTableByIndex(XWPFDocument xdoc, int pos) {
		Iterator<IBodyElement> bodyElement = xdoc.getBodyElementsIterator();
		int eIndex = 0, tableIndex = -1;
		while (bodyElement.hasNext()) {
			IBodyElement element = bodyElement.next();
			BodyElementType elementType = element.getElementType();
			if (elementType == BodyElementType.TABLE) {
				tableIndex++;
				if (tableIndex == pos) {
					break;
				}
			}
			eIndex++;
		}
		xdoc.removeBodyElement(eIndex);
	}

	/**
	 * 得到指定位置表格
	 * @param docx
	 * @param index
	 * @return
	 */
	public XWPFTable getTableByIndex(XWPFDocument docx, int index) {
		List<XWPFTable> tablesList = getAllTable(docx);
		if (tablesList == null || index < 0 || index > tablesList.size()) {
			return null;
		}
		return tablesList.get(index);
	}

	/**
	 * 得到所有表格
	 * @param xdoc
	 * @return
	 */
	public List<XWPFTable> getAllTable(XWPFDocument xdoc) {
		return xdoc.getTables();
	}

	/**
	 * 得到表格内容(第一次跨行单元格视为一个，第二次跳过跨行合并的单元格)
	 * @param table
	 * @return
	 */
	public List<List<String>> getTableRContent(XWPFTable table) {
		List<List<String>> tableContentList = new ArrayList<List<String>>();
		for (int rowIndex = 0, rowLen = table.getNumberOfRows(); rowIndex < rowLen; rowIndex++) {
			XWPFTableRow row = table.getRow(rowIndex);
			List<String> cellContentList = new ArrayList<String>();
			for (int colIndex = 0, colLen = row.getTableCells().size(); colIndex < colLen; colIndex++) {
				XWPFTableCell cell = row.getCell(colIndex);
				CTTc ctTc = cell.getCTTc();
				if (ctTc.isSetTcPr()) {
					CTTcPr tcPr = ctTc.getTcPr();
					if (tcPr.isSetHMerge()) {
						CTHMerge hMerge = tcPr.getHMerge();
						if (STMerge.RESTART.equals(hMerge.getVal())) {
							cellContentList.add(getTableCellContent(cell));
						}
					} else if (tcPr.isSetVMerge()) {
						CTVMerge vMerge = tcPr.getVMerge();
						if (STMerge.RESTART.equals(vMerge.getVal())) {
							cellContentList.add(getTableCellContent(cell));
						}
					} else {
						cellContentList.add(getTableCellContent(cell));
					}
				}
			}
			tableContentList.add(cellContentList);
		}
		return tableContentList;
	}

	/**
	 * 得到表格内容,合并后的单元格视为一个单元格
	 * @param table
	 * @return
	 */
	public List<List<String>> getTableContent(XWPFTable table) {
		List<List<String>> tableContentList = new ArrayList<List<String>>();
		for (int rowIndex = 0, rowLen = table.getNumberOfRows(); rowIndex < rowLen; rowIndex++) {
			XWPFTableRow row = table.getRow(rowIndex);
			List<String> cellContentList = new ArrayList<String>();
			for (int colIndex = 0, colLen = row.getTableCells().size(); colIndex < colLen; colIndex++) {
				XWPFTableCell cell = row.getCell(colIndex);
				cellContentList.add(getTableCellContent(cell));
			}
			tableContentList.add(cellContentList);
		}
		return tableContentList;
	}
	
	/**
	 * 得到表格所有内容
	 * @param cell
	 * @return
	 */
	public String getTableCellContent(XWPFTableCell cell) {
		StringBuffer sb = new StringBuffer();
		List<XWPFParagraph> cellPList = cell.getParagraphs();
		if (cellPList != null && cellPList.size() > 0) {
			for (XWPFParagraph xwpfPr : cellPList) {
				List<XWPFRun> runs = xwpfPr.getRuns();
				if (runs != null && runs.size() > 0) {
					for (XWPFRun xwpfRun : runs) {
						sb.append(xwpfRun.getText(0));
					}
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 创建表格,创建后表格至少有1行1列,设置列宽
	 * @param xdoc
	 * @param rowSize
	 * @param cellSize
	 * @param isSetColWidth
	 * @param colWidths
	 * @return
	 */
	public XWPFTable createTable(XWPFDocument xdoc, int rowSize, int cellSize,
			boolean isSetColWidth, int[] colWidths) {
		XWPFTable table = xdoc.createTable(rowSize, cellSize);
		if (isSetColWidth) {
			CTTbl ttbl = table.getCTTbl();
			CTTblGrid tblGrid = ttbl.addNewTblGrid();
			for (int j = 0, len = Math.min(cellSize, colWidths.length); j < len; j++) {
				CTTblGridCol gridCol = tblGrid.addNewGridCol();
				gridCol.setW(new BigInteger(String.valueOf(colWidths[j])));
			}
		}
		return table;
	}

	/**
	 * 设置表格总宽度与水平对齐方式
	 * @param table
	 * @param width
	 * @param enumValue
	 */
	public void setTableWidthAndHAlign(XWPFTable table, String width,
			STJc.Enum enumValue) {
		CTTblPr tblPr = getTableCTTblPr(table);
		CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr
				.addNewTblW();
		if (enumValue != null) {
			CTJc cTJc = tblPr.addNewJc();
			cTJc.setVal(enumValue);
		}
		tblWidth.setW(new BigInteger(width));
		tblWidth.setType(STTblWidth.DXA);
	}

	/**
	 * 得到Table的CTTblPr,不存在则新建
	 * @param table
	 * @return
	 */
	public CTTblPr getTableCTTblPr(XWPFTable table) {
		CTTbl ttbl = table.getCTTbl();
		CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl
				.getTblPr();
		return tblPr;
	}

	/**
	 * 得到Table的边框,不存在则新建
	 * @param table
	 * @return
	 */
	public CTTblBorders getTableBorders(XWPFTable table) {
		CTTblPr tblPr = getTableCTTblPr(table);
		CTTblBorders tblBorders = tblPr.isSetTblBorders() ? tblPr
				.getTblBorders() : tblPr.addNewTblBorders();
		return tblBorders;
	}

	/**
	 * 设置表格边框样式
	 * @param table
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	public void setTableBorders(XWPFTable table, CTBorder left, CTBorder top,
			CTBorder right, CTBorder bottom) {
		CTTblBorders tblBorders = getTableBorders(table);
		if (left != null) {
			tblBorders.setLeft(left);
		}
		if (top != null) {
			tblBorders.setTop(top);
		}
		if (right != null) {
			tblBorders.setRight(right);
		}
		if (bottom != null) {
			tblBorders.setBottom(bottom);
		}
	}

	/**
	 * 表格指定位置插入一行, index为新增行所在的行位置(不能大于表行数)
	 * @param table
	 * @param index
	 */
	public void insertTableRowAtIndex(XWPFTable table, int index) {
		XWPFTableRow firstRow = table.getRow(0);
		XWPFTableRow row = table.insertNewTableRow(index);
		if (row == null) {
			return;
		}
		CTTbl ctTbl = table.getCTTbl();
		CTTblGrid tblGrid = ctTbl.getTblGrid();
		int cellSize = 0;
		boolean isAdd = false;
		if (tblGrid != null) {
			List<CTTblGridCol> gridColList = tblGrid.getGridColList();
			if (gridColList != null && gridColList.size() > 0) {
				isAdd = true;
				for (CTTblGridCol ctlCol : gridColList) {
					XWPFTableCell cell = row.addNewTableCell();
					setCellWidthAndVAlign(cell, ctlCol.getW().toString(),
							STTblWidth.DXA, null);
				}
			}
		}
		// 大部分都不会走到这一步
		if (!isAdd) {
			cellSize = getCellSizeWithMergeNum(firstRow);
			for (int i = 0; i < cellSize; i++) {
				row.addNewTableCell();
			}
		}
	}

	/**
	 * 删除表一行
	 * @param table
	 * @param index
	 */
	public void deleteTableRow(XWPFTable table, int index) {
		table.removeRow(index);
	}

	/**
	 * 统计列数(包括合并的列数)
	 * @param row
	 * @return
	 */
	public int getCellSizeWithMergeNum(XWPFTableRow row) {
		List<XWPFTableCell> firstRowCellList = row.getTableCells();
		int cellSize = firstRowCellList.size();
		for (XWPFTableCell xwpfTableCell : firstRowCellList) {
			CTTc ctTc = xwpfTableCell.getCTTc();
			if (ctTc.isSetTcPr()) {
				CTTcPr tcPr = ctTc.getTcPr();
				if (tcPr.isSetGridSpan()) {
					CTDecimalNumber gridSpan = tcPr.getGridSpan();
					cellSize += gridSpan.getVal().intValue() - 1;
				}
			}
		}
		return cellSize;
	}

	/**
	 * 得到CTTrPr,不存在则新建
	 * @param row
	 * @return
	 */
	public CTTrPr getRowCTTrPr(XWPFTableRow row) {
		CTRow ctRow = row.getCtRow();
		CTTrPr trPr = ctRow.isSetTrPr() ? ctRow.getTrPr() : ctRow.addNewTrPr();
		return trPr;
	}

	/**
	 * 设置行高
	 * @param row
	 * @param hight
	 * @param heigthEnum
	 */
	public void setRowHeight(XWPFTableRow row, String hight,
			STHeightRule.Enum heigthEnum) {
		CTTrPr trPr = getRowCTTrPr(row);
		CTHeight trHeight;
		if (trPr.getTrHeightList() != null && trPr.getTrHeightList().size() > 0) {
			trHeight = trPr.getTrHeightList().get(0);
		} else {
			trHeight = trPr.addNewTrHeight();
		}
		trHeight.setVal(new BigInteger(hight));
		if (heigthEnum != null) {
			trHeight.setHRule(heigthEnum);
		}
	}

	/**
	 * 隐藏行
	 * @param row
	 * @param hidden
	 */
	public void setRowHidden(XWPFTableRow row, boolean hidden) {
		CTTrPr trPr = getRowCTTrPr(row);
		CTOnOff hiddenValue;
		if (trPr.getHiddenList() != null && trPr.getHiddenList().size() > 0) {
			hiddenValue = trPr.getHiddenList().get(0);
		} else {
			hiddenValue = trPr.addNewHidden();
		}
		if (hidden) {
			hiddenValue.setVal(STOnOff.TRUE);
		} else {
			hiddenValue.setVal(STOnOff.FALSE);
		}
		setRowAllCellHidden(row, hidden);
	}

	private void setRowAllCellHidden(XWPFTableRow row, boolean isVanish) {
		for (int colIndex = 0, colLen = row.getTableCells().size(); colIndex < colLen; colIndex++) {
			XWPFTableCell cell = row.getCell(colIndex);
			setCellHidden(cell, isVanish);
		}
	}

	/**
	 * 设置单元格内容
	 * @param table
	 * @param rowIndex
	 * @param col
	 * @param content
	 */
	public void setCellNewContent(XWPFTable table, int rowIndex, int col,String content) {
		XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
		XWPFParagraph p = getCellFirstParagraph(cell);
		List<XWPFRun> cellRunList = p.getRuns();
		if (cellRunList == null || cellRunList.size() == 0) {
			return;
		}
		for (int i = cellRunList.size() - 1; i >= 1; i--) {
			p.removeRun(i);
		}
		XWPFRun run = cellRunList.get(0);
		run.setText(content);
	}
	
	/**
	 * 判断此段落含有图片
	 * @Title: hasPicture  
	 * @Description: TODO  
	 * @param @param paragraph
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean hasPicture(XWPFParagraph paragraph) {
		if(null == paragraph) return false;
		List<XWPFRun> runs = paragraph.getRuns();
		for (XWPFRun run : runs) {
			CTR ctr = run.getCTR();
			CTPicture[] ctps = ctr.getPictArray();
			if(ctps.length > 0) return true;
		}
		
		return false;
	}
	
	/**
	 * 删除指定断落内的图片
	 * @Title: deletePicture  
	 * @Description: TODO  
	 * @param @param table
	 * @param @param index
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean deletePicture(XWPFTable table, int index) {
		List<XWPFTableRow> rows = table.getRows();
		for (XWPFTableRow row : rows) {
			List<XWPFTableCell> cells = row.getTableCells();
			for (XWPFTableCell cell : cells) {
				List<XWPFParagraph> paragraphs = cell.getParagraphs();
				for (XWPFParagraph paragraph : paragraphs) {
					if(deletePicture(paragraph, index)) return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 删除指定断落内的图片
	 * @Title: deletePicture  
	 * @Description: TODO  
	 * @param @param paragraph
	 * @param @param index
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean deletePicture(XWPFParagraph paragraph, int index) {
		if(null == paragraph) return false;
		List<XWPFRun> runs = paragraph.getRuns();
		for (XWPFRun run : runs) {
			CTR ctr = run.getCTR();
			CTPicture[] ctps = ctr.getPictArray();
			int size = ctps.length;
			for (int i = 0; i < size; i++) {
				if(index == 0) {
					ctr.removePict(i);
					return true;
				}
				index--;
			}
			CTDrawing[] ctds = ctr.getDrawingArray();
			size = ctds.length;
			for (int i = 0; i < size; i++) {
				if(index == 0) {
					ctr.removeDrawing(i);
					return true;
				}
				index--;
			}
		}
		return false;
	}
	
	/**
	 * 删除表格指定段落
	 * @param cell
	 * @param pos
	 * @return
	 */
	public boolean deleteCellParagraph(XWPFTableCell cell, int pos) {
		List<XWPFParagraph> paragraphs = cell.getParagraphs();
		if(paragraphs.size() > pos) {
			cell.removeParagraph(pos);
			return true;
		}
		return false;
	}
	
	/**
	 * 删除表格指定段落
	 * @param cell
	 * @param paragraph
	 * @return
	 */
	public boolean deleteCellParagraph(XWPFTableCell cell, XWPFParagraph paragraph) {
		List<XWPFParagraph> paragraphs = cell.getParagraphs();
		int size = paragraphs.size();
		for (int i = 0; i < size; i++) {
			if(paragraphs.get(i).equals(paragraph)) {
				cell.removeParagraph(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 删除表格指定段落
	 * @param paragraph
	 * @return
	 */
	public boolean deleteCellParagraph(XWPFParagraph paragraph) {
		List<XWPFTable> tables = paragraph.getDocument().getTables();
		boolean result = false;
		for (XWPFTable table : tables) {
			result = deleteCellParagraph(table, paragraph);
			if(result) return true;
		}
		return false;
	}
	
	/**
	 * 删除表格指定段落
	 * @param table
	 * @param paragraph
	 * @return
	 */
	public boolean deleteCellParagraph(XWPFTable table, XWPFParagraph paragraph) {
		List<XWPFTableRow> rows = table.getRows();
		boolean result = false;
		for (XWPFTableRow row : rows) {
			List<XWPFTableCell> cells = row.getTableCells();
			for (XWPFTableCell cell : cells) {
				result = deleteCellParagraph(cell, paragraph);
				if(result) return true;
			}
		}
		return false;
	}
	
	private void deleteRuns(XWPFParagraph paragraph, List<XWPFRun> cellRunList) {
		for (int i = cellRunList.size() - 1; i >= 0; i--) {
			paragraph.removeRun(i);
		}
	}
	
	/**
	 * 删除单元格内容
	 * @param table
	 * @param rowIndex
	 * @param col
	 * @return 
	 */
	public XWPFRun deleteCellContent(XWPFTable table, int rowIndex, int col) {
		XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
		XWPFParagraph paragraph = getCellFirstParagraph(cell);
		List<XWPFRun> cellRunList = paragraph.getRuns();
		if (cellRunList == null || cellRunList.size() == 0) {
			if(null != paragraph) return paragraph.createRun();
		}
		deleteRuns(paragraph, cellRunList);
		return paragraph.createRun();
	}
	
	/**
	 * 删除单元格内容
	 * @param table
	 * @param rowIndex
	 * @param col
	 * @return 
	 */
	public XWPFRun deleteCellContent(XWPFTableCell cell) {
		XWPFParagraph paragraph = getCellFirstParagraph(cell);
		List<XWPFRun> cellRunList = paragraph.getRuns();
		if (cellRunList == null || cellRunList.size() == 0) {
			if(null != paragraph) return paragraph.createRun();
		}
		deleteRuns(paragraph, cellRunList);
		return paragraph.createRun();
	}

	/**
	 * 隐藏单元格内容
	 * @param table
	 * @param rowIndex
	 * @param col
	 */
	public void setHiddenCellContent(XWPFTable table, int rowIndex, int col) {
		XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
		setCellHidden(cell, true);
	}

	public void setCellHidden(XWPFTableCell cell, boolean isVanish) {
		XWPFParagraph p = getCellFirstParagraph(cell);
		CTPPr pPPr = getParagraphCTPPr(p);
		CTParaRPr paRpr = pPPr.isSetRPr() ? pPPr.getRPr() : pPPr.addNewRPr();
		CTOnOff vanishCtOnOff = paRpr.isSetVanish() ? paRpr.getVanish() : paRpr
				.addNewVanish();
		if (isVanish) {
			vanishCtOnOff.setVal(STOnOff.TRUE);
		} else {
			vanishCtOnOff.setVal(STOnOff.FALSE);
		}
		List<XWPFRun> cellRunList = p.getRuns();
		if (cellRunList == null || cellRunList.size() == 0) {
			return;
		}
		for (XWPFRun xwpfRun : cellRunList) {
			CTRPr pRpr = getRunCTRPr(p, xwpfRun);
			vanishCtOnOff = pRpr.isSetVanish() ? pRpr.getVanish() : pRpr
					.addNewVanish();
			if (isVanish) {
				vanishCtOnOff.setVal(STOnOff.TRUE);
			} else {
				vanishCtOnOff.setVal(STOnOff.FALSE);
			}
		}
	}

	/**
	 * 得到Cell的CTTcPr,不存在则新建
	 * @param cell
	 * @return
	 */
	public CTTcPr getCellCTTcPr(XWPFTableCell cell) {
		CTTc cttc = cell.getCTTc();
		CTTcPr tcPr = cttc.isSetTcPr() ? cttc.getTcPr() : cttc.addNewTcPr();
		return tcPr;
	}

	/**
	 * 设置垂直对齐方式
	 * @param cell
	 * @param vAlign
	 */
	public void setCellVAlign(XWPFTableCell cell, STVerticalJc.Enum vAlign) {
		setCellWidthAndVAlign(cell, null, null, vAlign);
	}

	/**
	 * 设置列宽和垂直对齐方式
	 * @param cell
	 * @param width
	 * @param optionType
	 * @param vAlign
	 */
	public void setCellWidthAndVAlign(XWPFTableCell cell, String width,
			STTblWidth.Enum optionType, STVerticalJc.Enum vAlign) {
		CTTcPr tcPr = getCellCTTcPr(cell);
		CTTblWidth tcw = tcPr.isSetTcW() ? tcPr.getTcW() : tcPr.addNewTcW();
		if (width != null) {
			tcw.setW(new BigInteger(width));
		}
		if (optionType != null) {
			tcw.setType(optionType);
		}
		if (vAlign != null) {
			CTVerticalJc vJc = tcPr.isSetVAlign() ? tcPr.getVAlign() : tcPr
					.addNewVAlign();
			vJc.setVal(vAlign);
		}
	}

	/**
	 * 得到单元格第一个Paragraph
	 * @param cell
	 * @return
	 */
	public XWPFParagraph getCellFirstParagraph(XWPFTableCell cell) {
		XWPFParagraph p;
		if (cell.getParagraphs() != null && cell.getParagraphs().size() > 0) {
			p = cell.getParagraphs().get(0);
		} else {
			p = cell.addParagraph();
		}
		return p;
	}

	/**
	 * 跨列合并
	 * @param table
	 * @param row
	 * @param fromCell
	 * @param toCell
	 */
	public void mergeCellsHorizontal(XWPFTable table, int row, int fromCell,
			int toCell) {
		for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
			XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
			if (cellIndex == fromCell) {
				// The first merged cell is set with RESTART merge value
				getCellCTTcPr(cell).addNewHMerge().setVal(STMerge.RESTART);
			} else {
				// Cells which join (merge) the first one,are set with CONTINUE
				getCellCTTcPr(cell).addNewHMerge().setVal(STMerge.CONTINUE);
			}
		}
	}

	/**
	 * 跨行合并
	 * @param table
	 * @param col
	 * @param fromRow
	 * @param toRow
	 */
	public void mergeCellsVertically(XWPFTable table, int col, int fromRow,
			int toRow) {
		for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
			XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
			if (rowIndex == fromRow) {
				getCellCTTcPr(cell).addNewVMerge().setVal(STMerge.RESTART);
			} else {
				getCellCTTcPr(cell).addNewVMerge().setVal(STMerge.CONTINUE);
			}
		}
	}

	/**
	 * 设置页面背景色
	 * @param document
	 * @param bgColor
	 */
	public void setDocumentbackground(XWPFDocument document, String bgColor) {
		CTBackground bg = document.getDocument().isSetBackground() ? document
				.getDocument().getBackground() : document.getDocument()
				.addNewBackground();
		bg.setColor(bgColor);
	}

	
	public CTSectPr getDocumentCTSectPr(XWPFDocument document) {
		CTSectPr sectPr = document.getDocument().getBody().isSetSectPr() ? document
				.getDocument().getBody().getSectPr()
				: document.getDocument().getBody().addNewSectPr();
		return sectPr;
	}

	/**
	 * 页面Break
	 * @param document
	 * @param breakType
	 */
	public void addNewPageBreak(XWPFDocument document, BreakType breakType) {
		XWPFParagraph xp = document.createParagraph();
		xp.createRun().addBreak(breakType);
	}

	/**
	 * 设置页面边框
	 * @param document
	 * @param top
	 * @param right
	 * @param bottom
	 * @param left
	 */
	public void setPgBorders(XWPFDocument document, CTBorder top,
			CTBorder right, CTBorder bottom, CTBorder left) {
		CTSectPr sectPr = getDocumentCTSectPr(document);
		CTPageBorders pd = sectPr.isSetPgBorders() ? sectPr.getPgBorders()
				: sectPr.addNewPgBorders();
		/*
		 * CTBorder bb = pd.addNewBottom(); bb.setVal(STBorder.SINGLE);
		 * bb.setSz(new BigInteger("4")); bb.setSpace(new BigInteger("24"));
		 * bb.setColor("FBB61F");
		 */
		if (top != null) {
			pd.setTop(top);
		}
		if (right != null) {
			pd.setRight(right);
		}
		if (bottom != null) {
			pd.setBottom(bottom);
		}
		if (left != null) {
			pd.setLeft(left);
		}
	}

	/**
	 * 设置页面大小及纸张方向 landscape横向
	 * @param document
	 * @param width
	 * @param height
	 * @param stValue
	 */
	public void setDocumentSize(XWPFDocument document, String width,
			String height, STPageOrientation.Enum stValue) {
		CTSectPr sectPr = getDocumentCTSectPr(document);
		CTPageSz pgsz = sectPr.isSetPgSz() ? sectPr.getPgSz() : sectPr
				.addNewPgSz();
		pgsz.setH(new BigInteger(height));
		pgsz.setW(new BigInteger(width));
		pgsz.setOrient(stValue);
	}

	/**
	 * 设置页边距 (word中1厘米约等于567)
	 * @param document
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	public void setDocumentMargin(XWPFDocument document, String left,
			String top, String right, String bottom) {
		CTSectPr sectPr = getDocumentCTSectPr(document);
		CTPageMar ctpagemar = sectPr.addNewPgMar();
		if (StringUtils.isNotBlank(left)) {
			ctpagemar.setLeft(new BigInteger(left));
		}
		if (StringUtils.isNotBlank(top)) {
			ctpagemar.setTop(new BigInteger(top));
		}
		if (StringUtils.isNotBlank(right)) {
			ctpagemar.setRight(new BigInteger(right));
		}
		if (StringUtils.isNotBlank(bottom)) {
			ctpagemar.setBottom(new BigInteger(bottom));
		}
	}
	
	/**
	 * 替换
	 * @param paragraph
	 * @param key
	 * @param value
	 * @return
	 */
	public XWPFRun replaceParagraph(XWPFParagraph paragraph, String key, String value) {
		TextSegement textSegement = searchText(paragraph, key);
		if(null == textSegement) return null;
		XWPFRun run = clearData(paragraph, textSegement, key);
		run.setText(value);
		return run;
	}
	
	/**
	 * 搜索指定段落内容
	 * @param paragraph
	 * @param key
	 * @return
	 */
	private TextSegement searchText(XWPFParagraph paragraph, String key) {
		PositionInParagraph positionInParagraph = new PositionInParagraph();
		return paragraph.searchText(key, positionInParagraph);
	}
	
	/**
	 * 清空Run （text）
	 * @param run
	 * @return
	 */
	public XWPFRun clearXWPFRun(XWPFRun run) {
		CTR ctr = run.getCTR();
		for (int i = ctr.sizeOfTArray() - 1; i >= 0; i--) {
			ctr.removeT(i);
		}
		return run;
	}
	
	/**
	 * 清空主题color
	 * @Title clearThemeColor
	 * @Description TODO
	 * @param run void
	 */
	public void clearThemeColor(XWPFRun run) {
		CTR ctr = run.getCTR();
		if(ctr.isSetRPr()) {
			CTRPr rpr = ctr.getRPr();
			if(rpr.isSetColor()) {
				CTColor ctc = rpr.getColor();
				if(ctc.isSetThemeColor()) {
					ctc.unsetThemeColor();
				}
				
			}
		}
	}
	
	/**
	 * 搜索全文
	 * @param docx
	 * @param key
	 * @param clear
	 * @return
	 */
	public XWPFRun searchAll(XWPFDocument docx, String key, boolean clear) {
		OptionType[] paras = {OptionType.TABLE, OptionType.PARAGRAPH, OptionType.HEADER, OptionType.FOOTER};
		XWPFRun run = null;
		for (OptionType type : paras) {
			run = search(docx, key, type, clear);
			if(null != run) return run;
		}
		return run;
	}
	
	/**
	 * 按类型搜索全文
	 * @param docx
	 * @param key
	 * @param optionType
	 * @param clear
	 * @return
	 */
	public XWPFRun search(XWPFDocument docx, String key, OptionType optionType, boolean clear) {
		switch (optionType) {
		case TABLE:
			List<XWPFTable> tables = getAllTable(docx);
	        for (XWPFTable table : tables) {
	        	XWPFRun run = searchTable(table, key, clear);
	        	if(null != run) return run;
	        }
			break;
		case PARAGRAPH:
			List<XWPFParagraph> paragraphs = getAllParagraphs(docx);
	        for (XWPFParagraph paragraph : paragraphs) {
	        	XWPFRun run = searchParagraph(paragraph, key, clear);
	        	if(null != run) return run;
	        }
			break;
		case HEADER:
			List<XWPFFooter> footers = getAllFooters(docx);
			for (XWPFFooter footer : footers) {
				XWPFRun run = searchFooter(footer, key, clear);
				if(null != run) return run;
			}
			break;
		case FOOTER:
			List<XWPFHeader> headers = getAllHeaders(docx);
			for (XWPFHeader header : headers) {
				XWPFRun run = searchHeader(header, key, clear);
				if(null != run) return run;
			}
			break;
		default:
			return null;
		}
		return null;
	}
	
	/**
	 * 得到所有页眉
	 * @param docx
	 * @return
	 */
	public List<XWPFHeader> getAllHeaders(XWPFDocument docx) {
		return docx.getHeaderList();
	}
	
	/**
	 * 得到所有页脚
	 * @param docx
	 * @return
	 */
	public List<XWPFFooter> getAllFooters(XWPFDocument docx) {
		return docx.getFooterList();
	}
	
	/**
	 * 得到所有段落
	 * @param docx
	 * @return
	 */
	public List<XWPFParagraph> getAllParagraphs(XWPFDocument docx) {
		return docx.getParagraphs();
	}
	
	/**
	 * 搜索页眉
	 * @param header
	 * @param key
	 * @param clear
	 * @return
	 */
	public XWPFRun searchHeader(XWPFHeader header, String key, boolean clear) {
		List<XWPFParagraph> paragraphs = header.getParagraphs();
		return searchParagraphs(paragraphs, key, clear);
	}
	
	/**
	 * 搜索页脚
	 * @param footer
	 * @param key
	 * @param clear
	 * @return
	 */
	public XWPFRun searchFooter(XWPFFooter footer, String key, boolean clear) {
		List<XWPFParagraph> paragraphs = footer.getParagraphs();
		return searchParagraphs(paragraphs, key, clear);
	}
	
	/**
	 * 搜索
	 * @param paragraphs
	 * @param key
	 * @param clear
	 * @return
	 */
	public XWPFRun searchParagraphs(List<XWPFParagraph> paragraphs, String key, boolean clear) {
		XWPFRun run = null;
		for (XWPFParagraph paragraph : paragraphs) {
			if((run = searchParagraph(paragraph, key, clear)) != null) {
				return run;
			}
		}
		return run;
	}
	
	/**
	 * 到搜索的位置后面创建run
	 * @param paragraph
	 * @param textSegement
	 * @param key
	 * @return
	 */
	private XWPFRun createRun(XWPFParagraph paragraph, TextSegement textSegement, String key) {
		int beginRun = textSegement.getBeginRun();
		int endRun = textSegement.getEndRun();
		StringBuffer sb = new StringBuffer();
		List<XWPFRun> runs = paragraph.getRuns();
		for (int i = beginRun; i <= endRun; i++) {
			sb.append(runs.get(i).toString().trim());
		}
		if(sb.toString().equals(key)) {
			XWPFRun run = paragraph.insertNewRun(endRun + 1);
			copyBasicStyle(runs.get(endRun), run);
			return run;
		} else {
			return dealRun(paragraph, textSegement, key, sb, false);
		}
	}
	
	/**
	 * 清除搜索到的内容
	 * @param paragraph
	 * @param textSegement
	 * @param key
	 * @return
	 */
	public XWPFRun clearData(XWPFParagraph paragraph, TextSegement textSegement, String key) {
		int beginRun = textSegement.getBeginRun();
		int endRun = textSegement.getEndRun();
		StringBuffer sb = new StringBuffer();
		List<XWPFRun> runs = paragraph.getRuns();
		for (int i = beginRun; i <= endRun; i++) {
			sb.append(runs.get(i).toString().trim());
		}
		if(sb.toString().equals(key)) {
			removeRun(paragraph, beginRun, endRun - beginRun - 1);
			return clearXWPFRun(runs.get(beginRun));
		} else {
			return dealRun(paragraph, textSegement, key, sb, true);
		}
	}
	
	/**
	 * 删除指定个数run
	 * @param paragraph
	 * @param beginRun
	 * @param num
	 */
	private void removeRun(XWPFParagraph paragraph, int beginRun, int num) {
		while (num >= 0) {
			paragraph.removeRun(beginRun);
			num--;
		}
	}
	
	/**
	 * 创建指定个数run
	 * @param paragraph
	 * @param runs
	 * @param beginRun
	 * @param num
	 */
	private void createRun(XWPFParagraph paragraph, List<XWPFRun> runs, int beginRun, int num) {
		while (num > 0) {
			paragraph.insertNewRun(beginRun);
			copyBasicStyle(runs.get(beginRun + 1), runs.get(beginRun));
			num--;
		}
	}
	
	/**
	 *	处理run
	 * @param paragraph
	 * @param textSegement
	 * @param key
	 * @param sb
	 * @param clear
	 * @return
	 */
	private XWPFRun dealRun(XWPFParagraph paragraph, TextSegement textSegement, String key, StringBuffer sb, boolean clear) {
		int beginRun = textSegement.getBeginRun();
		int endRun = textSegement.getEndRun();
		List<XWPFRun> runs = paragraph.getRuns();
		int startIndex = sb.indexOf(key);
		String befor = null, end = null;
		if(startIndex > 0) {
			befor = sb.substring(0, startIndex);
		}
		if(startIndex < sb.length() - key.length()) {
			end = sb.substring(startIndex + key.length());
		}
		int runNum = endRun - beginRun;
		if(!clear) {
			if(null == befor || (null != befor && null != end)) {
				if(runNum > 2) {
					removeRun(paragraph, beginRun + 3, runNum - 3);
				} else {
					createRun(paragraph, runs, beginRun, 2 - runNum);
				}
				clearXWPFRun(runs.get(beginRun)).setText(null == befor ? key : befor + key);
				clearXWPFRun(runs.get(beginRun + 2)).setText(end);
				return clearXWPFRun(runs.get(beginRun + 1));
			} else {
				if(runNum > 1) {
					removeRun(paragraph, beginRun + 2, runNum - 2);
				} else {
					createRun(paragraph, runs, beginRun, 1 - runNum);
				}
				clearXWPFRun(runs.get(beginRun)).setText(befor + key);
				return clearXWPFRun(runs.get(beginRun + 1));
			}
		} else {
			if(null == befor || null == end) {
				if(runNum > 1) {
					removeRun(paragraph, beginRun + 2, runNum - 2);
				} else {
					createRun(paragraph, runs, beginRun, 1 - runNum);
				}
				if(null == befor) {
					clearXWPFRun(runs.get(beginRun + 1)).setText(end);
					return clearXWPFRun(runs.get(beginRun));
				} else {
					clearXWPFRun(runs.get(beginRun)).setText(befor);
					return clearXWPFRun(runs.get(beginRun + 1));
				}
			} else {
				if(runNum > 2) {
					removeRun(paragraph, beginRun + 3, runNum - 3);
				} else {
					createRun(paragraph, runs, beginRun, 2 - runNum);
				}
				clearXWPFRun(runs.get(beginRun)).setText(befor);
				clearXWPFRun(runs.get(beginRun + 2)).setText(end);
				return clearXWPFRun(runs.get(beginRun + 1));
				
			}
		}
	}
	
	
	/**
	 * 拷贝基本样式
	 * @param orig
	 * @param dest
	 */
	private void copyBasicStyle(XWPFRun orig, XWPFRun dest) {
		dest.setBold(orig.isBold());
		dest.setCharacterSpacing(orig.getCharacterSpacing());
		dest.setColor(orig.getColor());
		dest.setDoubleStrikethrough(orig.isDoubleStrikeThrough());
		dest.setEmbossed(orig.isEmbossed());
		dest.setFontFamily(orig.getFontFamily());
//		dest.setFontSize(orig.getFontSize());
		dest.setUnderline(orig.getUnderline());
		dest.setSubscript(orig.getSubscript());
		dest.setStrikeThrough(orig.isStrikeThrough());
		dest.setShadow(orig.isShadowed());
		dest.setKerning(orig.getKerning());
		dest.setItalic(orig.isItalic());
		dest.setImprinted(orig.isImprinted());
	}
	
	/**
	 * 搜索段落
	 * @param paragraph
	 * @param key
	 * @param clear
	 * @return
	 */
	public XWPFRun searchParagraph(XWPFParagraph paragraph, String key, boolean clear) {
		if(paragraph.getText().trim().contains(key)) {
			TextSegement textSegement = searchText(paragraph, key);
			if(null == textSegement) return null;
			if(clear) return clearData(paragraph, textSegement, key);
			return createRun(paragraph, textSegement, key);
		}
		return null;
	}
	
	/**
	 * 搜索
	 * @param table
	 * @param key
	 * @param clear
	 * @return
	 */
	public XWPFTableCell searchTableCell(XWPFTable table, String key, boolean clear) {
    	List<XWPFTableRow> rows = table.getRows();
    	for (XWPFTableRow row : rows) {
    		List<XWPFTableCell> cells = row.getTableCells();
    		for (XWPFTableCell cell : cells) {
    			if(cell.getText().trim().contains(key)) {
    				if(clear) { 
	    				List<XWPFParagraph> paragraphs = cell.getParagraphs();
	    				int size = paragraphs.size();
	    				for (int i = 0; i < size; i++) {
	    					deleteCellParagraph(cell, i);
						}
    				}
    				return cell;
    			}
    		}
    	}
    	return null;
	}
	
	/**
	 * 搜索
	 * @param docx
	 * @param key
	 * @param clear
	 * @return
	 */
	public XWPFTableCell searchTableCell(XWPFDocument docx, String key, boolean clear) {
		List<XWPFTable> tables = docx.getTables();
		XWPFTableCell cell = null;
		for (XWPFTable table : tables) {
			if(null != (cell = searchTableCell(table, key, clear))) return cell;
		}
    	return cell;
	}
	
	/**
	 * 搜索
	 * @param cell
	 * @param key
	 * @param clear
	 * @return
	 */
	public XWPFRun searchTableCell(XWPFTableCell cell, String key, boolean clear) {
		XWPFRun run = null;
		if(cell.getText().trim().contains(key)) {
			List<XWPFParagraph> paragraphs = cell.getParagraphs();
			int size = paragraphs.size();
			for (int i = 0; i < size; i++) {
				run = searchParagraph(paragraphs.get(i), key, clear);
				if(null != run) {
					return run;
				}
			}
		}
		return run;
	}
	
	/**
	 * 搜索
	 * @param tables
	 * @param key
	 * @param clear
	 * @return
	 */
	public XWPFRun searchTables(List<XWPFTable> tables, String key, boolean clear) {
		XWPFRun run = null;
		for (XWPFTable table : tables) {
			if((run = searchTable(table, key, clear)) != null) {
				return run;
			}
		}
		return run;
	}
	
	/**
	 * 搜索
	 * @param tables
	 * @param key
	 * @param clear
	 * @return
	 */
	public XWPFTableCell searchTableCell(List<XWPFTable> tables, String key, boolean clear) {
		XWPFTableCell cell = null;
		for (XWPFTable table : tables) {
			if((cell = searchTableCell(table, key, clear)) != null) {
				return cell;
			}
		}
    	return cell;
	}
	
	/**
	 * 搜索
	 * @param table
	 * @param key
	 * @param clear
	 * @return
	 */
	public XWPFRun searchTable(XWPFTable table, String key, boolean clear) {
		XWPFTableCell cell = searchTableCell(table, key, false);
		if(null == cell) return null;
    	List<XWPFParagraph> paragraphs = cell.getParagraphs();
    	return searchParagraphs(paragraphs, key, clear);
	}
	
	/**
	 * 移动到cell （自动识别合并单元格）
	 * @param cell
	 * @param col
	 * @param colBefore
	 * @return
	 */
	public XWPFTableCell moveToCell(XWPFTableCell cell, int col, boolean colBefore) {
		XWPFTableRow row = cell.getTableRow();
		List<XWPFTableCell> cells = row.getTableCells();
		XWPFTableCell tempCell = null;
		if(colBefore) {
			if(getColNum(cell) - col < 0) throw new IndexOutOfBoundsException("超出范围");
			tempCell = cells.get(getColNum(cell) - col);
		} else {
			if(getColNum(cell) + col > cells.size() - 1) throw new IndexOutOfBoundsException("超出范围");
			tempCell = cells.get(getColNum(cell) + col);
		}
		CTTcPr tcpr = tempCell.getCTTc().getTcPr();
		if(tcpr.getVMerge() == null && tcpr.getGridSpan() == null) {
			return tempCell;
		} else {
			if(tcpr.getVMerge() != null) {
				XWPFTableCell tempC = findMergeValue(tempCell, true, true);
				if(null == tempC) tempC = findMergeValue(tempCell, true, false);
				return tempC;
			} else {
				XWPFTableCell tempC = findMergeValue(tempCell, false, true);
				if(null == tempC) tempC = findMergeValue(tempCell, false, false);
				return tempC;
			}
		}
	}
	
	/**
	 * 处理单元格情况
	 * @param cell
	 * @param vertical
	 * @param before
	 * @return
	 */
	private XWPFTableCell findMergeValue(XWPFTableCell cell, boolean vertical, boolean before) {
		if(StringUtils.isNotEmpty(cell.getText())) return cell;
		XWPFTableRow row = cell.getTableRow();
		List<XWPFTableRow> rows = row.getTable().getRows();
		int cellBasic = getColNum(cell);
		int rowBasic = getRowNum(cell);
		XWPFTableCell tempCell = null;
		CTTcPr pr = null;
		boolean flag = before ? rowBasic >= 0 : rowBasic < rows.size();
		if(vertical) {
			while (flag) {
				tempCell = rows.get(rowBasic).getCell(cellBasic);
				pr = tempCell.getCTTc().getTcPr();
				if(pr.getVMerge() != null) {
					if(pr.getVMerge().getVal() == null) {
						if(StringUtils.isNotEmpty(tempCell.getText())) {
							return tempCell;
						}
					} else if("restart".equals(pr.getVMerge().getVal().toString())) {
						if(StringUtils.isNotEmpty(tempCell.getText())) {
							return tempCell;
						}
						return null;
					}
				}
				rowBasic = before ? rowBasic - 1 : rowBasic + 1;
				flag = before ? rowBasic >= 0 : rowBasic < rows.size();
			}
		} else {
			//暂时不用处理
		}
		return null;
	}
	
	/**
	 * 移动到cell
	 * @param cell
	 * @param row
	 * @param rowBefore
	 * @param col
	 * @param colBefore
	 * @return
	 */
	public XWPFTableCell moveToCell(XWPFTableCell cell, int row, boolean rowBefore, int col, boolean colBefore) {
		XWPFTable table = cell.getTableRow().getTable();
		List<XWPFTableRow> rows = table.getRows();
		XWPFTableRow tempRow = null;
		XWPFTableCell tempCell = null;
		List<XWPFTableCell> cells = null;
		if(rowBefore) {
			if(getRowNum(cell) - row < 0) throw new IndexOutOfBoundsException("超出范围");
			tempRow = rows.get(getRowNum(cell) - row);
		} else {
			if(getRowNum(cell) + row > rows.size() - 1) throw new IndexOutOfBoundsException("超出范围");
			tempRow = rows.get(getRowNum(cell) + row);
		}
		cells = tempRow.getTableCells();
		if(colBefore) {
			if(getColNum(cell) - col < 0) throw new IndexOutOfBoundsException("超出范围");
			tempCell = cells.get(getColNum(cell) - col);
		} else {
			if(getColNum(cell) + col > cells.size() - 1) throw new IndexOutOfBoundsException("超出范围");
			tempCell = cells.get(getColNum(cell) - col);
		}
		return tempCell;
	}
	
	/**
	 * 设置单元格内部基本样式
	 * @param cell
	 * @param fontFamily
	 * @param fontSize
	 * @param color
	 */
	public void setCellInnerInfo(XWPFTableCell cell, String fontFamily, String fontSize, String color) {
		List<XWPFParagraph> paragraphs = cell.getParagraphs();
		for (XWPFParagraph paragraph : paragraphs) {
			List<XWPFRun> runs = paragraph.getRuns();
			for (XWPFRun run : runs) {
				if(null != color) {
					run.setColor(color);
				}
				setParagraphRunFontInfo(paragraph, run, null, fontFamily, fontSize);
			}
		}
	}
	
	/**
	 * 得到行号
	 * @param cell
	 * @return
	 */
	public int getRowNum(XWPFTableCell cell) {
		XWPFTable table = cell.getTableRow().getTable();
		List<XWPFTableRow> rows = table.getRows();
		return rows.indexOf(cell.getTableRow());
	}
	
	/**
	 * 得到列号
	 * @param cell
	 * @return
	 */
	public int getColNum(XWPFTableCell cell) {
		XWPFTableRow row = cell.getTableRow();
		List<XWPFTableCell> cells = row.getTableCells();
		return cells.indexOf(cell);
	}
	
	/**
	 * 替换
	 * @param paragraph
	 * @param paras
	 */
	public void replaceParagraph(XWPFParagraph paragraph, Map<String, String> paras) {
		paras.forEach((key, value) -> {
			replaceParagraph(paragraph, key, value);
		});
	}
	
	/**
	 * 插入图片
	 * @param run
	 * @param is
	 * @param XWPFDocument
	 * @param pictureName
	 * @param width
	 * @param height
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public void insertPicture(XWPFRun run, InputStream is, int XWPFDocument, String pictureName, int width, int height) throws InvalidFormatException, IOException {
		run.addPicture(is, XWPFDocument, pictureName, Units.toEMU(width), Units.toEMU(height));
	}
	
	/**
	 * 替换
	 * @param table
	 * @param paras
	 */
	public void replaceTable(XWPFTable table, Map<String, String> paras) {
		for (Entry<String, String> en : paras.entrySet()) {
			XWPFRun run = searchTable(table, en.getKey(), true);
			if(null != run) run.setText(en.getValue());
		}
	}
	
	/**
	 * 替换页脚
	 * @param docx
	 * @param paras
	 */
	public void replaceFooter(XWPFDocument docx, Map<String, String> paras) {
		List<XWPFFooter> footers = getAllFooters(docx);
		paras.forEach((key, value) -> {
			footers.forEach(footer -> {
				XWPFRun run = searchFooter(footer, key, true);
				if(run != null) {
					run.setText(value);
					return;
				}
			});
		});
	}
	
	/**
	 * 替换页眉
	 * @param docx
	 * @param paras
	 */
	public void replaceHeader(XWPFDocument docx, Map<String, String> paras) {
		List<XWPFHeader> footers = getAllHeaders(docx);
		paras.forEach((key, value) -> {
			footers.forEach(footer -> {
				XWPFRun run = searchHeader(footer, key, true);
				if(run != null) {
					run.setText(value);
					return;
				}
			});
		});
	}
	
	/**
	 * 按类型替换
	 * @param docx
	 * @param paras
	 * @param optionType
	 */
	public void replace(XWPFDocument docx, Map<String, String> paras, OptionType optionType) {
		for (Entry<String, String> en : paras.entrySet()) {
			replace(docx, en.getKey(), en.getValue(), optionType);
		}
	}
	
	/**
	 * 按类型替换
	 * @param docx
	 * @param key
	 * @param value
	 * @param optionType
	 */
	public void replace(XWPFDocument docx, String key, String value, OptionType optionType) {
		switch (optionType) {
		case TABLE:
			List<XWPFTable> tables = getAllTable(docx);
	        for (XWPFTable table : tables) {
	        	XWPFRun run = searchTable(table, key, true);
	        	if(null != run) {
	        		run.setText(key);
	        		break;
	        	}
	        }
			break;
		case PARAGRAPH:
			List<XWPFParagraph> paragraphs = getAllParagraphs(docx);
	        for (XWPFParagraph paragraph : paragraphs) {
	        	XWPFRun run = searchParagraph(paragraph, key, true);
	        	if(null != run) {
	        		run.setText(key);
	        		break;
	        	}
	        }
			break;
		case HEADER:
			List<XWPFFooter> footers = getAllFooters(docx);
			for (XWPFFooter footer : footers) {
				XWPFRun run = searchFooter(footer, key, true);
				if(null != run) {
	        		run.setText(key);
	        		break;
	        	}
			}
			break;
		case FOOTER:
			List<XWPFHeader> headers = getAllHeaders(docx);
			for (XWPFHeader header : headers) {
				XWPFRun run = searchHeader(header, key, true);
				if(null != run) {
	        		run.setText(key);
	        		break;
	        	}
			}
			break;
		default:
		}
	}
	
	/**
	 * 全文搜索并替换
	 * @param docx
	 * @param key
	 * @param value
	 */
	public void replaceAll(XWPFDocument docx, String key, String value) {
		searchAll(docx, key, true).setText(value);
	}
	
	/**
	 * 全文搜索并替换
	 * @param docx
	 * @param paras
	 */
	public void replaceAll(XWPFDocument docx, Map<String, String> paras) {
		paras.forEach((key, value) -> {
			XWPFRun run = searchAll(docx, key, true);
			if(null != run) run.setText(value);
		});
	}
	
	
	/**
	 * 设置段落、行、缩进等
	 * @param paragraph
	 * @param paragraphSpacing
	 * @param lineSpacing
	 * @param indentation
	 */
	public void setParagraphInfo(XWPFParagraph paragraph, Integer paragraphSpacing, Integer lineSpacing, Integer indentation) {
		if(null != paragraphSpacing) {
			paragraph.setSpacingBefore(paragraphSpacing);
			paragraph.setSpacingAfter(paragraphSpacing);
		}
		if(null != lineSpacing) {
			paragraph.setSpacingBeforeLines(lineSpacing);
			paragraph.setSpacingAfterLines(lineSpacing);
		}
		if(null != indentation) {
			paragraph.setIndentationFirstLine(indentation);
		}
	}
	
	/**
	 * 插入Run
	 * @param paragraph
	 * @param pos
	 * @return
	 */
	public XWPFRun insertXWPFRun(XWPFParagraph paragraph, int pos) {
		return paragraph.insertNewRun(pos);
	}
	
	/**
	 * 替换
	 * @param tables
	 * @param paras
	 */
	public void replaceTables(List<XWPFTable> tables, Map<String, String> paras) {
		for (XWPFTable table : tables) {
			replaceTable(table, paras);
		}
	}
	
	/**
	 * 替换
	 * @param paragraphs
	 * @param paras
	 */
	public void replaceParagraphs(List<XWPFParagraph> paragraphs, Map<String, String> paras) {
		for (XWPFParagraph paragraph : paragraphs) {
			replaceParagraph(paragraph, paras);
		}
	}

	/**
	 * 另存文档
	 * @param docx
	 * @param savePath
	 * @throws Exception
	 */
	public void saveAs(XWPFDocument docx, String savePath)
			throws Exception {
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(savePath));
		docx.write(out);
		out.close();
	}

	/**
	 * 打开word文档
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public XWPFDocument openDocument(String filePath) throws Exception {
//		POIXMLDocument.openPackage(filePath)
		ZipSecureFile.setMinInflateRatio(0L);
		is = new BufferedInputStream(new FileInputStream(new File(filePath)));
		docx = new XWPFDocument(is);
		return docx;
	}
	
	/**
	 * 打开word文档
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public XWPFDocument openDocument(InputStream is) throws Exception {
//		POIXMLDocument.openPackage(filePath)
		this.is = is;
		docx = new XWPFDocument(is);
		return docx;
	}
	
	/**
	 * 关闭文档(不一定有用)
	 * @param isSave
	 */
	public void closeDocument(boolean isSave) {
		try {
			if(isSave) {
				if(docx != null) {
					docx.close();
					docx = null;
				}
			} else {
				if(is != null) {
					is.close();
					is = null;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 合并文件
	 * @param sources
	 * @return
	 * @throws Exception
	 */
	public static InputStream mergeDocx(List<InputStream> sources) throws Exception {
		WordprocessingMLPackage target = null;
		final File generated = File.createTempFile("generated", ".docx");

		int chunkId = 0;
		Iterator<InputStream> it = sources.iterator();
		while (it.hasNext()) {
			InputStream is = it.next();
			if (is != null) {
				if (target == null) {
					OutputStream os = new FileOutputStream(generated);
					os.write(IOUtils.toByteArray(is));
					os.close();
					os = null;
					target = WordprocessingMLPackage.load(generated);
				} else {
					insertDocx(target.getMainDocumentPart(), IOUtils.toByteArray(is), chunkId++);
				}
				is.close();
			}
		}
		
		if (target != null) {
			target.save(generated);
			return new BufferedInputStream(new FileInputStream(generated));
		} else {
			return null;
		}

	}

	/**
	 * 插入文件
	 * @param main
	 * @param bytes
	 * @param chunkId
	 * @throws Exception
	 */
	private static void insertDocx(MainDocumentPart main, byte[] bytes, int chunkId) throws Exception {
		AlternativeFormatInputPart afiPart = new AlternativeFormatInputPart(new PartName("/part" + chunkId + ".docx"));
		afiPart.setContentType(new ContentType(CONTENT_TYPE));
		afiPart.setBinaryData(bytes);
		Relationship altChunkRel = main.addTargetPart(afiPart);

		CTAltChunk chunk = Context.getWmlObjectFactory().createCTAltChunk();
		chunk.setId(altChunkRel.getId());

		main.addObject(chunk);
	}
	
	/**
	 * 
	 * @Title: insertPictureInline  
	 * @Description: TODO  
	 * @param @param docx
	 * @param @param is
	 * @param @param XWPFDocument
	 * @param @param width
	 * @param @param height
	 * @param @param run
	 * @param @throws InvalidFormatException
	 * @return void
	 * @throws
	 */
	public void insertPictureInline(XWPFDocument docx, InputStream is, int XWPFDocument, int width, int height,
			XWPFRun run) throws InvalidFormatException {
		if (run == null) {
			return;
		}
		String blipId = docx.addPictureData(is, XWPFDocument);
		int id = docx.getNextPicNameNumber(XWPFDocument);
		final int EMU = 9525;
		width *= EMU;
		height *= EMU;
		// String blipId =
		// getAllPictures().get(id).getPackageRelationship().getId();
		
		CTInline inline = run.getCTR().addNewDrawing().addNewInline();
		String picXml = ""
				+ "<a:graphic xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">"
				+ "   <a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">"
				+ "      <pic:pic xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">"
				+ "         <pic:nvPicPr>" + "            <pic:cNvPr id=\""
				+ id
				+ "\" name=\"img_"
				+ id
				+ "\"/>"
				+ "            <pic:cNvPicPr/>"
				+ "         </pic:nvPicPr>"
				+ "         <pic:blipFill>"
				+ "            <a:blip r:embed=\""
				+ blipId
				+ "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/>"
				+ "            <a:stretch>"
				+ "               <a:fillRect/>"
				+ "            </a:stretch>"
				+ "         </pic:blipFill>"
				+ "         <pic:spPr>"
				+ "            <a:xfrm>"
				+ "               <a:off x=\"0\" y=\"0\"/>"
				+ "               <a:ext cx=\""
				+ width
				+ "\" cy=\""
				+ height
				+ "\"/>"
				+ "            </a:xfrm>"
				+ "            <a:prstGeom prst=\"rect\">"
				+ "               <a:avLst/>"
				+ "            </a:prstGeom>"
				+ "         </pic:spPr>"
				+ "      </pic:pic>"
				+ "   </a:graphicData>" 
				+ "</a:graphic>";
		// CTGraphicalObjectData graphicData =
		// inline.addNewGraphic().addNewGraphicData();
		XmlToken xmlToken = null;
		try {
			xmlToken = XmlToken.Factory.parse(picXml);
		} catch (XmlException xe) {
			xe.printStackTrace();
		}
		inline.set(xmlToken);
		// graphicData.set(xmlToken);
		inline.setDistT(0);
		inline.setDistB(0);
		inline.setDistL(0);
		inline.setDistR(0);
		CTPositiveSize2D extent = inline.addNewExtent();
		extent.setCx(width);
		extent.setCy(height);
		CTNonVisualDrawingProps docPr = inline.addNewDocPr();
		docPr.setId(id);
		docPr.setName("docx_img_" + id);
		docPr.setDescr("docx Picture");
	}
	
	/**
	 * 
	 * @Title: insertPictureAnchor  
	 * @Description: TODO  
	 * @param @param docx
	 * @param @param is
	 * @param @param XWPFDocument
	 * @param @param wrapType
	 * @param @param width
	 * @param @param height
	 * @param @param posH
	 * @param @param posV
	 * @param @param run
	 * @param @return
	 * @param @throws InvalidFormatException
	 * @param @throws XmlException
	 * @return CTAnchor
	 * @throws
	 */
	public CTAnchor insertPictureAnchor(XWPFDocument docx, InputStream is, int XWPFDocument, WrapType wrapType, int width, int height, int posH, int posV,
			XWPFRun run) throws InvalidFormatException, XmlException {
		if (run == null) {
			return null;
		}
		String blipId = docx.addPictureData(is, XWPFDocument);
		int id = docx.getNextPicNameNumber(XWPFDocument);
		final int EMU = 9525;
		width *= EMU;
		height *= EMU;
		posH *= EMU;
		posV *= EMU;
		CTAnchor cta = run.getCTR().addNewDrawing().addNewAnchor();
		cta.setRelativeHeight(251659264);
		cta.setDistB(0L);
		cta.setDistT(0L);
		cta.setDistL(0L);
		cta.setDistR(0L);
		cta.setAllowOverlap(false);
		cta.setLayoutInCell(false);
		cta.setLocked(true);
		cta.setSimplePos2(false);
		cta.setBehindDoc(false);
		
		CTPoint2D ctp2 = cta.addNewSimplePos();
		ctp2.setX(0L);
		ctp2.setY(0L);
		
		XmlObject xo = wrapType.getInstance();
		switch (wrapType.index) {
		case 0:
			cta.setWrapNone((CTWrapNone) xo);
			break;
		case 1:
			cta.setWrapSquare((CTWrapSquare) xo);
			break;
		case 2:
			cta.setWrapThrough((CTWrapThrough) xo);
			break;
		case 3:
			cta.setWrapTight((CTWrapTight) xo);
			break;
		case 4:
			cta.setWrapTopAndBottom((CTWrapTopBottom) xo);
			break;
		default:
			break;
		}
//		CTWrapTopBottom ctwtb =  cta.addNewWrapTopAndBottom();
//		ctwtb.setDistB(0L);
//		ctwtb.setDistT(0L);
		
		CTNonVisualDrawingProps docPr = cta.addNewDocPr();
		docPr.setId(id);
		docPr.setName("docx_img_" + id);
		docPr.setDescr("docx Picture");
		
		CTEffectExtent extent = cta.addNewEffectExtent();
		extent.setB(0L);
		extent.setL(0L);
		extent.setR(0L);
		extent.setT(0L);
		
		CTPosH ctph = cta.addNewPositionH();
		ctph.setRelativeFrom(STRelFromH.COLUMN);
		ctph.setPosOffset(posH);
		
		CTPosV ctpv = cta.addNewPositionV();
		ctpv.setRelativeFrom(STRelFromV.PARAGRAPH);
		ctpv.setPosOffset(posV);
      
      	CTNonVisualGraphicFrameProperties ctnvgfp = cta.addNewCNvGraphicFramePr();
      	CTGraphicalObjectFrameLocking  ctgofl = ctnvgfp.addNewGraphicFrameLocks();
      	ctgofl.setNoChangeAspect(true);
      	ctgofl.selectAttribute(new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "graphicFrameLocks"));
      	
		CTPositiveSize2D ctps = cta.addNewExtent();
		ctps.setCx(width);
		ctps.setCy(height);
		
		CTGraphicalObject ctgo = cta.addNewGraphic();
		
		String picXml = "<a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\" xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">" +
							"<pic:pic xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">" +
								"<pic:nvPicPr>" +
									"<pic:cNvPr id=\"" + id + "\" name=\"docx_img_" + id + "\"/>" +
									"<pic:cNvPicPr/>" +
								"</pic:nvPicPr>" +
								"<pic:blipFill>" +
									"<a:blip r:embed=\"" + blipId + "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/>" +
									"<a:stretch>" +
										"<a:fillRect/>" +
									"</a:stretch>" +
								"</pic:blipFill>" +
								"<pic:spPr>" +
									"<a:xfrm>" +
										"<a:off x=\"0\" y=\"0\"/>" +
										"<a:ext cx=\"" + width + "\" cy=\"" + height + "\"/>" +
									"</a:xfrm>"+
									"<a:prstGeom prst=\"rect\">" +
										"<a:avLst/>" +
									"</a:prstGeom>" +
								"</pic:spPr>" +
							"</pic:pic>" +
						"</a:graphicData>";
		XmlToken xmlToken = null;
		try {
			xmlToken = XmlToken.Factory.parse(picXml);
		} catch (XmlException xe) {
			xe.printStackTrace();
		}
		ctgo.set(xmlToken);
		return cta;
	}
	
	public enum WrapType {
		
		NONE(0){
			@Override
			public XmlObject getInstance() {
				// TODO Auto-generated method stub
				CTWrapNone ctwn = CTWrapNone.Factory.newInstance();
				return ctwn;
			}
		},
		SQUARE(1) {
			@Override
			public XmlObject getInstance() {
				// TODO Auto-generated method stub
				CTWrapSquare ctws = CTWrapSquare.Factory.newInstance();
				ctws.setDistB(0L);
				ctws.setDistL(0L);
				ctws.setDistR(0L);
				ctws.setDistT(0L);
				return ctws;
			}
		},
		THROUGH(2) {
			@Override
			public XmlObject getInstance() {
				// TODO Auto-generated method stub
				CTWrapThrough ctwt = CTWrapThrough.Factory.newInstance();
				ctwt.setDistL(0L);
				ctwt.setDistR(0L);
				CTWrapPath ctwp = CTWrapPath.Factory.newInstance();
				ctwp.setEdited(false);
				ctwt.setWrapPolygon(ctwp);
				ctwt.setWrapText(STWrapText.BOTH_SIDES);
				return ctwt;
			}
		},
		TIGHT(3) {
			@Override
			public XmlObject getInstance() {
				// TODO Auto-generated method stub
				CTWrapTight ctwt = CTWrapTight.Factory.newInstance();
				ctwt.setDistL(0L);
				ctwt.setDistR(0L);
				CTWrapPath ctwp = CTWrapPath.Factory.newInstance();
				ctwp.setEdited(false);
				ctwt.setWrapPolygon(ctwp);
				ctwt.setWrapText(STWrapText.BOTH_SIDES);
				return ctwt;
			}
		},
		TOPANDBOTTOM(4) {
			@Override
			public XmlObject getInstance() {
				// TODO Auto-generated method stub
				CTWrapTopBottom ctwtb = CTWrapTopBottom.Factory.newInstance();
				ctwtb.setDistB(0L);
				ctwtb.setDistT(0L);
				return ctwtb;
			}
		};
		
		private int index;
		private WrapType(int index) {
			this.index = index;
		}
		
		public XmlObject getInstance() {
			return null;
		}
		
	}
	
	public enum OptionType {
		PARAGRAPH,
		TABLE,
		FOOTER,
		HEADER
	}

}

