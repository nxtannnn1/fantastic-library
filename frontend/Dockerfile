#DOCKERFILE-FRONTEND


# Use a imagem oficial do Nginx
FROM nginx:alpine

# Copia os arquivos estáticos para a pasta servida pelo Nginx
COPY . /usr/share/nginx/html

# Expõe a porta 80
EXPOSE 80

# Inicia o Nginx
CMD ["nginx", "-g", "daemon off;"]
