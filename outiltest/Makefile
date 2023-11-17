### VARIABLES ###

SRC_DIR = src
BUILD_DIR = build
OUT_DIR = out
COMMON_DIR = ../common/src
DOC_DIR = doc
MARIADB_JAR = ../common/lib/mariadb-client.jar

CLASSPATH = $(BUILD_DIR):$(COMMON_DIR):$(MARIADB_JAR)

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none -d $(BUILD_DIR) -cp $(CLASSPATH)

### TARGETS ###

build: outiltest.jar

clean:
	rm -rf $(BUILD_DIR)/ $(OUT_DIR)/ outiltest.jar $(DOC_DIR)/

doc:
	javadoc -d $(DOC_DIR) $(shell find $(SRC_DIR) -name "*.java") $(shell find $(COMMON_DIR) -name "*.java") -cp $(CLASSPATH)

.PHONY: build clean doc classfiles

### RULES ###

outiltest.jar: classfiles
	mkdir -p $(BUILD_DIR) $(OUT_DIR)
	cp $(MARIADB_JAR) $(OUT_DIR)/.
	jar cfm $(OUT_DIR)/outiltest.jar $(SRC_DIR)/fr/iutfbleau/but2/sae312023/outiltest/Manifest.txt -C $(BUILD_DIR) .


classfiles:
	$(JC) $(JCFLAGS) $(shell find $(COMMON_DIR) -name "*.java") $(shell find $(SRC_DIR) -name "*.java")