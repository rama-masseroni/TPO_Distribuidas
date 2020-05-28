USE [CRUCIS]
GO
/****** Object:  Table [dbo].[Usuarios]    Script Date: 28/05/2020 18:01:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Usuarios](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[idUsr] [varchar](45) NOT NULL,
	[password] [varchar](20) NOT NULL,
	[dni] [int] NOT NULL,
	[nombre] [varchar](45) NOT NULL,
	[apellido] [varchar](45) NOT NULL,
	[fechaDeNacimiento] [date] NOT NULL,
	[sexo] [char](1) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
