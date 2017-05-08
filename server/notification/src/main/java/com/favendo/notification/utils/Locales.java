package com.favendo.notification.utils;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.Entry;

public enum Locales {
    EN_US("en-us", Locale.US),
    IN_ID("in-id", LocaleConstants.LOCALE_INDONESIA );

    private String code;

    private Locale locale;

    Locales(String code, Locale locale) {
        this.code = code;
        this.locale = locale;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public static final Map<String, Locale> localeByCode = populateLocaleByCode();
    private static final Map<Locale, String> codeByLocale = populateCodeByLocale();

    public static Locale getLocaleByCode(String code) {
        return localeByCode.getOrDefault(code.toLowerCase(), EN_US.getLocale());
    }

    public static String getCodeByLocale(Locale locale) {
        return codeByLocale.getOrDefault(locale, EN_US.getCode());
    }

    private static Map<String, Locale> populateLocaleByCode() {
        return Collections.unmodifiableMap(Stream.of(
            new SimpleEntry<>(EN_US.getCode(), EN_US.getLocale()),
            new SimpleEntry<>(IN_ID.getCode(), IN_ID.getLocale()))
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
    }

    private static Map<Locale, String> populateCodeByLocale() {
        return Collections.unmodifiableMap(Stream.of(
            new SimpleEntry<>(EN_US.getLocale(), EN_US.getCode()),
            new SimpleEntry<>(IN_ID.getLocale(), IN_ID.getCode()))
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
    }

    private static class LocaleConstants {
        public static final Locale LOCALE_INDONESIA = new Locale ("in", "id");
    }
}
