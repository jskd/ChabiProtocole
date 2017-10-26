CC = javac

BIN_DIR = bin/
SRC_DIR = src/

RENDU1_PATH = rendu1
RENDU1_SRC = $(RENDU1_PATH)/rendu1.tex
RENDU1_OUT = $(RENDU1_PATH)/rendu1.pdf

pdf:
	pdflatex -output-directory $(RENDU1_PATH) $(RENDU1_SRC)
	rm */*.aux */*.log

#tar: clean
#	tar -zcvf PooCAv_Project_G7.tar.gz .gitignore Makefile LICENSE projetv1.pdf README.md UML_v1.pdf deliverables/ bin/ src/
