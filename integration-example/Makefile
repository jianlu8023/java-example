#CUR_DIR:=$(notdir $(shell dirname $(abspath $(MAKEFILE_LIST))))
#CDIR:=$(dir $(abspath $(MAKEFILE_LIST)))
#CDIRNAME:=$(shell dirname $(CDIR))
#
#DOCKER_FILE:= $(lastword $(CUR_DIR))/Dockerfile
DOCKER_FILE:=Dockerfile
CUR_TIME:= $(shell date +"%Y%m%d%H%M")
IMAGE_VERSION:=v$(CUR_TIME)
IMAGE_PREFIX:=java-demo/alpine/integration
IMAGE_NAME:=$(IMAGE_PREFIX):$(IMAGE_VERSION)

all: build
.PHONY: all

build:
	@echo "starting build image ..."
	docker buildx build -t "$(IMAGE_NAME)" --network host --no-cache -f "$(DOCKER_FILE)" .
	@echo "Image Name: $(IMAGE_NAME)"
	@if [ -f docker-compose.yaml ]; then \
		echo "docker-compose.yaml found, starting upgrade image version ..."; \
		sed -i "s@image:.*@image $(IMAGE_NAME)@g" docker-compose.yaml; \
	else \
	    echo "docker-compose.yaml not found, skipping upgrade image version ..."; \
	fi
.PHONY: build

clean:
	@echo "starting clean image and builder cache ..."
	@if [ -f docker-compose.yaml ]; then \
		echo "docker-compose.yaml already exists, down services ..."; \
		docker-compose down; \
	fi
	@if docker images | grep -q "$(IMAGE_PREFIX)"; then \
		echo "image found, starting clean image ..."; \
		docker rmi `docker images --format "{{.Repository}}:{{.Tag}}" | grep "$(IMAGE_PREFIX)"`; \
	else \
		echo "image not found, skipping clean image ..."; \
	fi
	docker builder prune --all --force
	@echo "clean done"
.PHONY: clean